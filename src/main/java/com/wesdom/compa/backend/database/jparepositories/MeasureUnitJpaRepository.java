package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.MeasureUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureUnitJpaRepository extends JpaRepository<MeasureUnit, Long>, JpaSpecificationExecutor<MeasureUnit> {
}
