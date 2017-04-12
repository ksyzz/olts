package org.ksyzz.repository;

import org.ksyzz.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by fengqian on 2017/4/12.
 */
@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer>, JpaSpecificationExecutor<Exam>{
}
