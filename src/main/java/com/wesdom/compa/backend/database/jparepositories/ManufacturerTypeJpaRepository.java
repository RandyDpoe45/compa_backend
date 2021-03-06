package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.ManufacturerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerTypeJpaRepository extends JpaRepository<ManufacturerType,Long> {
}
