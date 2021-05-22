package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipalityJpaRepository extends JpaRepository<Municipality,Long>, JpaSpecificationExecutor<Municipality> {
}
