package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.AssociationMember;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IAssociationMemberRepository {
    public AssociationMember get(Long id);
    public Page<AssociationMember> getAll(Map<String,String> params);
    public AssociationMember create(AssociationMember associationMember);
    public AssociationMember update(Long associationMemberId, AssociationMember associationMember);
    public AssociationMember getByAssociationIdAndManufacturerId(Long assId, Long manId);
    public void delete(Long associationMemberId);
}
