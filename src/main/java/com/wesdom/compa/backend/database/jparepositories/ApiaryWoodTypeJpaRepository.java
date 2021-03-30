package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.ApiaryWoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiaryWoodTypeJpaRepository extends JpaRepository<ApiaryWoodType,Long>, JpaSpecificationExecutor<ApiaryWoodType> {
}
