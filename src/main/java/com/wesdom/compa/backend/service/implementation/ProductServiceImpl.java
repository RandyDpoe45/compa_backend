package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.model.ProductInStateSegment;
import com.wesdom.compa.backend.database.model.Request;
import com.wesdom.compa.backend.database.repositories.IProductInStateSegmentRepository;
import com.wesdom.compa.backend.database.repositories.IProductRepository;
import com.wesdom.compa.backend.database.repositories.IRequestRepository;
import com.wesdom.compa.backend.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.compa.backend.exceptionhandling.exceptions.GeneralException;
import com.wesdom.compa.backend.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IProductInStateSegmentRepository productInStateSegmentRepository;

    @Autowired
    private IRequestRepository requestRepository;

    @Override
    public void deleteProduct(Long id) {
        ProductInStateSegment productInStateSegment =
                productInStateSegmentRepository.findTop1ByProductId(id);
        if(productInStateSegment != null){
            throw new GeneralException(
                    ExceptionCodesEnum.PRODUCT_IN_USE,
                    "Este producto no puede ser eliminado porque esta en uso."
            );
        }else {
            Request request = requestRepository.findTop1ByProductId(id);
            if(request != null){
                throw new GeneralException(
                        ExceptionCodesEnum.PRODUCT_IN_USE,
                        "Este producto no puede ser eliminado porque esta en uso."
                );
            }
            productRepository.delete(id);
        }
    }
}
