package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.ProductionActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionActivityJpaRepository extends JpaRepository<ProductionActivity,Long>,
        JpaSpecificationExecutor<ProductionActivity> {

    ProductionActivity findTop1ByProductInStateSegmentId(Long id);
}
