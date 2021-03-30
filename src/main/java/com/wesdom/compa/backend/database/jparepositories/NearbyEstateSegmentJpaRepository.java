package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.NearbyEstateSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NearbyEstateSegmentJpaRepository extends JpaRepository<NearbyEstateSegment,Long>, JpaSpecificationExecutor<NearbyEstateSegment> {
}
