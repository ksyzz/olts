package org.ksyzz.repository;

import org.ksyzz.entity.Account;
import org.ksyzz.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fengqian on 2017/4/12.
 */
@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer>, JpaSpecificationExecutor<Exam>{
    @Query("select e from Exam e where e.account =?1")
    List<Exam> findByAccount(Account account);
}
