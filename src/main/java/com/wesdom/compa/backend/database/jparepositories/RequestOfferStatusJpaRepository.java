package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.RequestOfferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestOfferStatusJpaRepository extends JpaRepository<RequestOfferStatus,Long>
        , JpaSpecificationExecutor<RequestOfferStatus> {

    RequestOfferStatus findTop1ByCode(String code);
}
