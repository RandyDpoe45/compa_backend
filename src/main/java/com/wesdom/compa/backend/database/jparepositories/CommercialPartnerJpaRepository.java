package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.CommercialPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommercialPartnerJpaRepository extends JpaRepository<CommercialPartner,Long>, JpaSpecificationExecutor<CommercialPartner> {
}
