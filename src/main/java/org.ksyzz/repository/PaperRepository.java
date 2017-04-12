package org.ksyzz.repository;

import org.ksyzz.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by fengqian on 2017/4/11.
 */
@Repository
public interface PaperRepository extends JpaRepository<Paper, Integer>, JpaSpecificationExecutor<Paper>{
}
