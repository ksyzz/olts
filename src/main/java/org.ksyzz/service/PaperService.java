package org.ksyzz.service;

import org.ksyzz.entity.*;
import org.ksyzz.enums.QuestionType;
import org.ksyzz.exception.NullEntityException;
import org.ksyzz.info.OptionInfo;
import org.ksyzz.info.PaperInfo;
import org.ksyzz.repository.ExamRepository;
import org.ksyzz.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.ksyzz.util.ErrorMessage.nullExam;

/**
 * Created by fengqian on 2017/4/11.
 */
@Service
public class PaperService {
    @Autowired
    PaperRepository paperRepository;
    @Autowired
    ExamRepository examRepository;
    @Autowired
    QuestionService questionService;
    @Autowired
    OptionService optionService;

    /**
     * 添加答卷
     * @param account
     * @param paperInfo
     * @return
     */
    public Paper addPaper(Account account, PaperInfo paperInfo){
        Paper paper = new Paper();
        paper.setAccount(account);
        Exam exam = examRepository.findOne(paperInfo.getExamInfo().getExamId());
        if (exam == null)
            throw new NullEntityException(nullExam);
        paper.setExam(exam);
        if (paperInfo.getAnswerInfos() != null){
            int score = 0;
            List<Answer> answers = new ArrayList<>();
            paperInfo.getAnswerInfos().forEach(answerInfo -> {
                int question_score = 0;
                Answer answer = new Answer();
                Question question = questionService.findById(answerInfo.getQuestionInfo().getQuestionId());
                answer.setQuestion(question);
                if (question.getQuestionType() == QuestionType.ESSAY){
                    answer.setEssay_answer(answerInfo.getEssay_answer());
                }else {
                    List<Option> solutions = new ArrayList<Option>();
                    List<OptionInfo> optionInfos =  answerInfo.getAnswers();
                    if (optionInfos != null){
                        optionInfos.forEach(optionInfo -> {
                            solutions.add(optionService.findById(optionInfo.getOptionId()));
                        });
                        answer.setAnswers(solutions);
                        List<Option> correct_solutions = questionService.getCorrectOptions(question);
                        if ( solutions.size() == correct_solutions.size() && solutions.containsAll(correct_solutions))
                        {
                            question_score = question.getScore();
                        }
                    }
                    answer.setScore(question_score);
                    paper.calculateScore(question_score);
                }

                answers.add(answer);
            });
            paper.setAnswers(answers);
        }else {
            paper.setScore(0);
        }
        paperRepository.save(paper);
        return paper;
    }

    /**
     * 是否已经参加过考试
     */
    public boolean isAnswered(Account account, Exam exam){
        Paper paper = paperRepository.findByAccountAndExam(account, exam);
        if (paper != null)
            return true;
        else
            return false;
    }
}
