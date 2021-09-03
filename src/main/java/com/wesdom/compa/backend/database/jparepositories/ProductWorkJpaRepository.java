package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.estatesegment.ProductWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductWorkJpaRepository extends JpaRepository<ProductWork,Long>,
        JpaSpecificationExecutor<ProductWork> {
}
