package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.AssociationPromoter;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IAssociationPromoterRepository {
    public AssociationPromoter get(Long id);
    public Page<AssociationPromoter> getAll(Map<String,String> params);
    public AssociationPromoter save(AssociationPromoter associationPromoter);
    public AssociationPromoter update(Long associationPromoterId, AssociationPromoter associationPromoter);
    public AssociationPromoter getByAssociationIdAndPromoterId(Long assId, Long promId);
    public void delete(Long associationPromoterId);
}
