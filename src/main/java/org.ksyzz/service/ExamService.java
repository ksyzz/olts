package org.ksyzz.service;

import org.ksyzz.entity.Account;
import org.ksyzz.entity.Exam;
import org.ksyzz.exception.NullEntityException;
import org.ksyzz.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.ksyzz.util.ErrorMessage.nullExam;

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
}
