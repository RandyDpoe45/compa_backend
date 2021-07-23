package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.Advertising;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisingJpaRepository extends
        JpaRepository<Advertising,Long>, JpaSpecificationExecutor<Advertising> {
}
