package org.ksyzz.test;

import com.jayway.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ksyzz.entity.*;
import org.ksyzz.info.PaperInfo;
import org.ksyzz.repository.*;
import org.ksyzz.service.AccountService;
import org.ksyzz.service.AccountTokenService;
import org.ksyzz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by fengqian on 2017/4/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class MainTest {
//
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountTokenService accountTokenService;
    @Autowired
    QuestionService questionService;
    @Autowired
    PaperRepository paperRepository;
    @Autowired
    ExamRepository examRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    OptionRepository optionRepository;
    @Test
    public void testCreateAccount(){
//        List<Paper> papers = paperRepository.findOrderByScore();
//        System.out.println(papers.get(0).getId());
//        paperRepository.delete(11);
    }
    @Test
    public void testDeleteToken(){
        given()
                .header("token", "38bfda0c-819e-4570-967b-f2bbfb5195dd")
                .contentType(ContentType.JSON)
                .body("{\"examInfo\":{\"examId\":\"1\"}, \"answerInfos\":[{\"questionInfo\":{\"questionId\":\"2\"},\"answers\":[{\"optionId\":\"11\"}]},{\"questionInfo\":{\"questionId\":\"3\"},\"essay_answer\":\"answer\"}]}")
                .when()
                .post("/paper/add")
                .then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    public void testGetCorrectOption(){
        Question question = questionRepository.findOne(3);
        List<Option> options = questionService.getCorrectOptions(question);

        List<Option> solutions = new ArrayList<>();
        solutions.add(optionRepository.findOne(6));
        solutions.add(optionRepository.findOne(7));
        List<Integer> optionIds = options.stream().map(Option::getId).collect(Collectors.toList());
        List<Integer> solutionIds = solutions.stream().map(Option :: getId).collect(Collectors.toList());
        if (optionIds.size() == solutionIds.size() && solutionIds.containsAll(optionIds)){
            System.out.println(true);}
    }
    @Test
    public void testGetPapersByAccount(){
        Account account = accountRepository.findOne("ksyzz4");
        List<Paper> papers = paperRepository.findByAccount(account);
        papers.stream().map(PaperInfo::new).collect(Collectors.toList()).forEach(paper->{
            System.out.println(paper.getExamInfo().getTitle());
        });
    }
    @Test
    public void testAddquestion(){
        Exam exam = examRepository.findOne(2);
        Question question = new Question();
        question.setScore(5);
        exam.setScore(5);
        examRepository.save(exam);
        question.setExam(exam);
        questionRepository.save(question);
    }
}
