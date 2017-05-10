package org.ksyzz.service;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.ksyzz.entity.Account;
import org.ksyzz.entity.Exam;
import org.ksyzz.entity.Paper;
import org.ksyzz.exception.ConflictException;
import org.ksyzz.exception.NullEntityException;
import org.ksyzz.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 获取答卷成绩分布
     * @param examId
     */
    public void getPaperGrades(int examId, HttpServletResponse response) throws IOException {
        Exam exam = getExam(examId);
        int total_score = exam.getScore();
        Map<String, Integer> map = new HashMap<>();
        List<Paper> papers = exam.getPapers();
        papers.forEach(paper -> {
            int score = paper.getScore();
            int range = score == 0 ? 1 : (int)Math.ceil(((double)score)/10);
            String key = (range - 1) * 10 + "-" + 10 * range;
            Integer value = map.get(key);
            if (value == null){
                map.put(key, 1);
            }else {
                value++;
                map.put(key, value);
            }
        });
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i <= total_score/10; i++){
            String key = i * 10 + "-" + (i + 1)*10;
            if (map.get(key) != null){
                dataset.addValue(map.get(key), "", key);
            }else {
                dataset.addValue(0, "", key);
            }
        }

        JFreeChart chart = ChartFactory.createBarChart(" ", // chart title
                "score", // domain axis label
                "amount", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // 图标方向
                true, // 是否显示legend
                true, // 是否显示tooltips
                false // 是否显示URLs
        );
         ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, 400, 400, null);
//        ChartUtilities.writeBufferedImageAsJPEG(response.getOutputStream(), chart.createBufferedImage(800,800));
    }

    /**
     * 绘制直方图
//     */
//    private CategoryDataset createDataset(int examId){
//
//
//    }
}
