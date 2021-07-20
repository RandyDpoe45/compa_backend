package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.RequestOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestOfferJpaRepository extends JpaRepository<RequestOffer,Long>, JpaSpecificationExecutor<RequestOffer> {
}
