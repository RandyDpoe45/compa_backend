package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerJpaRepository extends JpaRepository<Manufacturer,Long>,JpaSpecificationExecutor<Manufacturer> {
}
