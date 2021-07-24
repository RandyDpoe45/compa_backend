package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.enums.BioProductTypeEnum;
import com.wesdom.compa.backend.database.model.BioProduct;
import com.wesdom.compa.backend.database.model.Promoter;
import com.wesdom.compa.backend.database.repositories.IBioProductRepository;
import com.wesdom.compa.backend.database.repositories.IPromoterRepository;
import com.wesdom.compa.backend.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.compa.backend.exceptionhandling.exceptions.GeneralException;
import com.wesdom.compa.backend.service.interfaces.IPromoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromoterServiceImpl implements IPromoterService {

    @Autowired
    private IPromoterRepository promoterRepository;

    @Autowired
    private IBioProductRepository bioProductRepository;

    @Override
    public Promoter createPromoter(Promoter promoter) {
        validateBioProducts(promoter.getSystemBioProductList());
        return promoterRepository.create(promoter);
    }

    @Override
    public Promoter updatePromoter(Long id, Promoter promoter) {
        validateBioProducts(promoter.getSystemBioProductList());
        return promoterRepository.update(id,promoter);
    }

    private void validateBioProducts(List<BioProduct> bioProductList){
        List<Long> ids = bioProductList.stream()
                .map(x -> x.getId())
                .collect(Collectors.toList());
        List<BioProduct> bioProducts = bioProductRepository.findAllById(ids);
        bioProducts.forEach(x -> {
            if(!x.getType().equals(BioProductTypeEnum.SYSTEM.getCode())){
                throw new GeneralException(
                        ExceptionCodesEnum.ADDRESS_NAME_IN_USE,
                        "No se puede asociar un Biopreparado que no sea del sistema"
                );
            }
        });
    }
}
