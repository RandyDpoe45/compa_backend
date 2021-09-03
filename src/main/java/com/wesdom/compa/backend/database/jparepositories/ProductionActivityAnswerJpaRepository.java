package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.activity.ProductionActivityAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionActivityAnswerJpaRepository extends JpaRepository<ProductionActivityAnswer,Long>,
        JpaSpecificationExecutor<ProductionActivityAnswer> {
}
