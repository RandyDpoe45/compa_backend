package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.AssociationMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociationMemberJpaRepository extends JpaRepository<AssociationMember,Long>, JpaSpecificationExecutor<AssociationMember> {

    @Query("SELECT am FROM AssociationMember am WHERE am.association.id = ?1 AND manufacturerGroup.id = ?2")
    AssociationMember getByAssociationIdAndManufacturerId(Long assId, Long manId);
}
