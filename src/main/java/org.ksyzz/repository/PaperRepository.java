package org.ksyzz.repository;

import org.ksyzz.entity.Account;
import org.ksyzz.entity.Exam;
import org.ksyzz.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fengqian on 2017/4/11.
 */
@Repository
public interface PaperRepository extends JpaRepository<Paper, Integer>, JpaSpecificationExecutor<Paper>{
    @Query("select p from Paper p where p.account = ?1 and p.exam = ?2")
    Paper findByAccountAndExam(Account account, Exam exam);
    @Query("select p from Paper p where p.account = ?1")
    List<Paper> findByAccount(Account account);
    @Query("select p from Paper p where p.exam = ?1 order by score")
    List<Paper> findByExam(Exam exam);
}
