package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.model.ProductInStateSegment;
import com.wesdom.compa.backend.database.repositories.IEstateSegmentRepository;
import com.wesdom.compa.backend.database.repositories.IProductInStateSegmentRepository;
import com.wesdom.compa.backend.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.compa.backend.exceptionhandling.exceptions.GeneralException;
import com.wesdom.compa.backend.service.interfaces.IEstateSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstateSegementServiceImpl implements IEstateSegmentService {

    @Autowired
    private IEstateSegmentRepository estateSegmentRepository;

    @Autowired
    private IProductInStateSegmentRepository productInStateSegmentRepository;

    @Override
    public void deleteEstateSegment(Long id) {
        List<ProductInStateSegment> products = productInStateSegmentRepository.getProductsByEstateSegmentId(id);
        if(!products.isEmpty()){
            throw new GeneralException(ExceptionCodesEnum.SEGMENT_WITH_PRODUCTS,"El segmento tiene productos asociados, por favor eliminelos primero");
        }
        estateSegmentRepository.delete(id);
    }
}
