package org.ksyzz.repository;

import org.ksyzz.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by fengqian on 2017/4/12.
 */
@Repository
public interface OptionRepository extends JpaRepository<Option, Integer>, JpaSpecificationExecutor<Option>{
    @Query("select o from Option o where o.content = ?1")
    Option findByContent(String content);
//    @Query("select o from Option o where o.correct = true")
//    List<Option> findCorrectOptions();
}
