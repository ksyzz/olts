package org.ksyzz.test;

import com.jayway.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ksyzz.repository.AccountRepository;
import org.ksyzz.repository.ExamRepository;
import org.ksyzz.repository.PaperRepository;
import org.ksyzz.repository.QuestionRepository;
import org.ksyzz.service.AccountService;
import org.ksyzz.service.AccountTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    QuestionRepository questionRepository;
    @Autowired
    PaperRepository paperRepository;
    @Autowired
    ExamRepository examRepository;
    @Test
    public void testCreateAccount(){

        paperRepository.delete(13);
    }
    @Test
    public void testDeleteToken(){
//        Account account = accountService.findById("ksyzz4");
//        String token = accountTokenService.createToken(account);
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
}
