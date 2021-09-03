package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.model.estatesegment.EstateSegment;
import com.wesdom.compa.backend.database.model.Product;
import com.wesdom.compa.backend.database.model.estatesegment.ProductInStateSegment;
import com.wesdom.compa.backend.database.model.activity.ProductionActivity;
import com.wesdom.compa.backend.database.repositories.IEstateSegmentRepository;
import com.wesdom.compa.backend.database.repositories.IProductInStateSegmentRepository;
import com.wesdom.compa.backend.database.repositories.IProductRepository;
import com.wesdom.compa.backend.database.repositories.IProductionActivityRepository;
import com.wesdom.compa.backend.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.compa.backend.exceptionhandling.exceptions.GeneralException;
import com.wesdom.compa.backend.service.interfaces.IProductInSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductInSegmentServiceImpl implements IProductInSegmentService{

    @Autowired
    private IProductInStateSegmentRepository productInStateSegmentRepository;

    @Autowired
    private IProductionActivityRepository productionActivityRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IEstateSegmentRepository estateSegmentRepository;

    @Override
    public ProductInStateSegment createProductInSegment(ProductInStateSegment productInStateSegment) {

        Product product = productRepository.get(productInStateSegment.getProduct().getId());
        EstateSegment estateSegment = estateSegmentRepository.get(productInStateSegment.getEstateSegment().getId());
        if(product.getProductType().getCode()
                .equals(estateSegment.getEstateSegmentType().getCode())){
            return productInStateSegmentRepository.save(productInStateSegment);
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
