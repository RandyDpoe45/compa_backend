package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.estatesegment.EstateSegmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EstateSegmentTypeJpaRepository extends JpaRepository<EstateSegmentType,Long>, JpaSpecificationExecutor<EstateSegmentType> {
}
