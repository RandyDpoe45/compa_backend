package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.model.activity.Activity;
import com.wesdom.compa.backend.database.model.ProductionStage;
import com.wesdom.compa.backend.database.repositories.IActivityRepository;
import com.wesdom.compa.backend.database.repositories.IProductionStageRepository;
import com.wesdom.compa.backend.dtos.ProductionStageOrderDto;
import com.wesdom.compa.backend.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.compa.backend.exceptionhandling.exceptions.GeneralException;
import com.wesdom.compa.backend.service.interfaces.IProductionStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductionStageServiceImpl implements IProductionStageService {

    @Autowired
    private IActivityRepository activityRepository;

    @Autowired
    private IProductionStageRepository productionStageRepository;

    @Override
    public ProductionStage save(ProductionStage productionStage) {
        ProductionStage last = productionStageRepository
                .getLastStageByEstateSegmentTypeId(
                        productionStage.getEstateSegmentType().getId()
                );
        System.out.println(last.getStageOrder());
        productionStage.setStageOrder(last.getStageOrder() + 1);
        return productionStageRepository.save(productionStage);
    }

    @Override
    public ProductionStage updateOrder(ProductionStageOrderDto productionStageOrderDto) {
        return null;
    }

    @Override
    public void deleteProductionStage(Long id) {
        Activity activity = activityRepository.findTop1ByProductionStageId(id);
        if(activity != null){
            throw new GeneralException(
                    ExceptionCodesEnum.PRODUCTION_STAGE_IN_USE,
                    "Esta fase de produccion no puede ser eliminada porque esta en uso."
            );
        }
        productionStageRepository.delete(id);
    }
}
