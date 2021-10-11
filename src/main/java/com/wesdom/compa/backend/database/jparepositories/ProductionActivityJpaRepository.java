package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.activity.ProductionActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionActivityJpaRepository extends JpaRepository<ProductionActivity,Long>,
        JpaSpecificationExecutor<ProductionActivity> {

    ProductionActivity findTop1ByProductInStateSegmentId(Long id);
    ProductionActivity findTop1ByActivityId(Long id);
}
