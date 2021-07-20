package com.wesdom.compa.backend.service.interfaces;

import com.wesdom.compa.backend.database.model.AssociationMember;
import com.wesdom.compa.backend.database.model.AssociationPromoter;

public interface IAssociationService {
    AssociationMember addMember(AssociationMember associationMember);
    AssociationPromoter addPromoter(AssociationPromoter associationPromoter);
}
