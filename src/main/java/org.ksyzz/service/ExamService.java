package org.ksyzz.service;

import org.ksyzz.entity.Account;
import org.ksyzz.entity.Exam;
import org.ksyzz.exception.ConflictException;
import org.ksyzz.exception.NullEntityException;
import org.ksyzz.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.ksyzz.util.ErrorMessage.nullExam;
import static org.ksyzz.util.ErrorMessage.passwordNotCorrect;

/**
 * Created by fengqian on 2017/4/19.
 */
@Service
public class ExamService {
    @Autowired
    ExamRepository examRepository;

    /**
     * 创建考试
     * @param title
     * @param password
     * @param time_limited
     * @param account
     * @return
     */
    public Exam createExam(String title, String password, int time_limited, Account account){
        Exam exam = new Exam();
        exam.setTitle(title);
        exam.setPassword(password);
        exam.setTime_limited(time_limited);
        exam.setAccount(account);
        examRepository.save(exam);
        return exam;
    }

    /**
     * 修改考试信息
     * @param id
     * @param title
     * @param password
     * @param time_limited
     * @return
     */
    public Exam modifyExam(int id, String title, String password, int time_limited){
        Exam exam = examRepository.findOne(id);
        if (exam == null){
            throw new NullEntityException(nullExam);
        }
        exam.setPassword(password);
        exam.setTitle(title);
        exam.setTime_limited(time_limited);
        examRepository.save(exam);
        return exam;
    }

    /**
     * 选择试卷
     * @param id
     * @param password
     * @return
     */
    public Exam joinExam(int id, String password){
        Exam exam = getExam(id);
        if (!exam.getPassword().equals(password)){
            throw new ConflictException(passwordNotCorrect);
        }
        return exam;
    }

    /**
     * 获取自己创建的考试
     * @param account
     * @return
     */
    public List<Exam> getExamByAccount(Account account){
        return examRepository.findByAccount(account);
    }
    public Exam getExam(int examId){
        Exam exam = examRepository.findOne(examId);
        if (exam == null){
            throw new NullEntityException(nullExam);
        }
        return exam;
    }
}
