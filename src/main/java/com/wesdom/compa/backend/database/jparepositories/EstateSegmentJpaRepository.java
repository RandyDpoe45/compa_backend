package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.estatesegment.EstateSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EstateSegmentJpaRepository extends JpaRepository<EstateSegment,Long>, JpaSpecificationExecutor<EstateSegment> {
}
