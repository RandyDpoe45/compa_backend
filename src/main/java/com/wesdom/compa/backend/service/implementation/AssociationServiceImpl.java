package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.model.AssociationMember;
import com.wesdom.compa.backend.database.model.AssociationPromoter;
import com.wesdom.compa.backend.database.repositories.IAssociationMemberRepository;
import com.wesdom.compa.backend.database.repositories.IAssociationPromoterRepository;
import com.wesdom.compa.backend.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.compa.backend.exceptionhandling.exceptions.GeneralException;
import com.wesdom.compa.backend.service.interfaces.IAssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociationServiceImpl implements IAssociationService {

    @Autowired
    private IAssociationMemberRepository associationMemberRepository;

    @Autowired
    private IAssociationPromoterRepository associationPromoterRepository;

    @Override
    public AssociationMember addMember(AssociationMember associationMember) {
        AssociationMember aux = associationMemberRepository.
                getByAssociationIdAndManufacturerId(
                        associationMember.getAssociation().getId(),
                        associationMember.getManufacturerGroup().getId());
        if(aux != null){
            throw new GeneralException(
                    ExceptionCodesEnum.GROUP_ALREADY_BINDED,
                    "El grupo  ya esta asociado a la asociacion");
        }
        return associationMemberRepository.save(associationMember);
    }

    @Override
    public AssociationPromoter addPromoter(AssociationPromoter associationPromoter) {
        AssociationPromoter aux = associationPromoterRepository.
                getByAssociationIdAndPromoterId(
                        associationPromoter.getAssociation().getId(),
                        associationPromoter.getPromoter().getId());
        if(aux != null){
            throw new GeneralException(
                    ExceptionCodesEnum.PROMOTER_ALREADY_BINDED,
                    "El promotor  ya esta asociado a la asociacion");
        }
        return associationPromoterRepository.save(associationPromoter);
    }
}
