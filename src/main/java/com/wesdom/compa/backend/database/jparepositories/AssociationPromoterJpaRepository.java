package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.AssociationPromoter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociationPromoterJpaRepository extends JpaRepository<AssociationPromoter,Long>, JpaSpecificationExecutor<AssociationPromoter> {

    @Query("SELECT ap FROM AssociationPromoter ap WHERE ap.association.id = ?1 AND ap.promoter.id = ?2")
    AssociationPromoter getByAssociationIdAndPromoterId(Long assId, Long promId);
}
