package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.model.ProductInStateSegment;
import com.wesdom.compa.backend.database.repositories.IProductInStateSegmentRepository;
import com.wesdom.compa.backend.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.compa.backend.exceptionhandling.exceptions.GeneralException;
import com.wesdom.compa.backend.service.interfaces.IProductInSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductInSegmentServiceImpl implements IProductInSegmentService{

    @Autowired
    private IProductInStateSegmentRepository productInStateSegmentRepository;

    @Override
    public ProductInStateSegment createProductInSegment(ProductInStateSegment productInStateSegment) {

        if(productInStateSegment.getProduct().getProductType().getCode()
                .equals(productInStateSegment.getEstateSegment().getEstateSegmentType().getCode())){
            return productInStateSegmentRepository.create(productInStateSegment);
        }else{
            throw new GeneralException(ExceptionCodesEnum.DIFFERENT_TYPE_PROD_SEG,"No se puede asociar este tipo de producto al segmento");
        }
    }
}
