package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.estatesegment.NearbyFlora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NearbyFloraJpaRepository extends JpaRepository<NearbyFlora,Long>, JpaSpecificationExecutor<NearbyFlora> {

    NearbyFlora findTop1ByFloraId(Long floraId);
}
