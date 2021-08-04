package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.ProductionStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionStageJpaRepository extends JpaRepository<ProductionStage,Long>, JpaSpecificationExecutor<ProductionStage> {

    ProductionStage findTop1ByEstateSegmentTypeIdOrderByStageOrderDesc(Long id);
    ProductionStage findTop1ByStageOrderAndEstateSegmentTypeId(Long stageOrder, Long estateSegmentTypeId);
}
