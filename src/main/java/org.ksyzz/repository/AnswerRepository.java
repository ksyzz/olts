package org.ksyzz.repository;

import org.ksyzz.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by fengqian on 2017/4/11.
 */
@Repository
public interface AnswerRepository extends JpaSpecificationExecutor<Answer>, JpaRepository<Answer, Integer>{
}
