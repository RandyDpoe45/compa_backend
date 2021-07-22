package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.model.ProductInStateSegment;
import com.wesdom.compa.backend.database.model.ProductionActivity;
import com.wesdom.compa.backend.database.repositories.IProductInStateSegmentRepository;
import com.wesdom.compa.backend.database.repositories.IProductionActivityRepository;
import com.wesdom.compa.backend.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.compa.backend.exceptionhandling.exceptions.GeneralException;
import com.wesdom.compa.backend.service.interfaces.IProductInSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInSegmentServiceImpl implements IProductInSegmentService{

    @Autowired
    private IProductInStateSegmentRepository productInStateSegmentRepository;

    @Autowired
    private IProductionActivityRepository productionActivityRepository;

    @Override
    public ProductInStateSegment createProductInSegment(ProductInStateSegment productInStateSegment) {

        if(productInStateSegment.getProduct().getProductType().getCode()
                .equals(productInStateSegment.getEstateSegment().getEstateSegmentType().getCode())){
            return productInStateSegmentRepository.create(productInStateSegment);
        }else{
            throw new GeneralException(
                    ExceptionCodesEnum.DIFFERENT_TYPE_PROD_SEG,
                    "No se puede asociar este tipo de producto al segmento"
            );
        }
    }

    @Override
    public void deleteProductInStateSegment(Long id) {
        ProductionActivity activity = productionActivityRepository.
                findTop1ByProductInStateSegmentId(id);
        if(activity != null){
            throw new GeneralException(
                    ExceptionCodesEnum.PRODUCT_WITH_ACTIVITIES,
                    "No se puede eliminar el producto, debido a que ya tiene actividades creadas"
            );
        }
        productInStateSegmentRepository.delete(id);
    }
}
