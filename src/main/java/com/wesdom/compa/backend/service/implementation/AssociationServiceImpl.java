package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.model.AssociationMember;
import com.wesdom.compa.backend.database.repositories.IAssociationMemberRepository;
import com.wesdom.compa.backend.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.compa.backend.exceptionhandling.exceptions.GeneralException;
import com.wesdom.compa.backend.service.interfaces.IAssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociationServiceImpl implements IAssociationService {

    @Autowired
    private IAssociationMemberRepository associationMemberRepository;

    @Override
    public AssociationMember addMember(AssociationMember associationMember) {
        AssociationMember aux = associationMemberRepository.
                getByAssociationIdAndManufacturerId(
                        associationMember.getAssociation().getId(),
                        associationMember.getManufacturer().getId());
        if(aux != null){
            throw new GeneralException(
                    ExceptionCodesEnum.ADDRESS_NAME_IN_USE,
                    "El productor ya esta asociado a la asociacion");
        }
        return associationMemberRepository.create(associationMember);
    }
}
