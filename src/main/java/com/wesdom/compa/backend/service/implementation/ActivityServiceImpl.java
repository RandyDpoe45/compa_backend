package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.model.activity.ProductionActivity;
import com.wesdom.compa.backend.database.repositories.IActivityRepository;
import com.wesdom.compa.backend.database.repositories.IProductionActivityRepository;
import com.wesdom.compa.backend.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.compa.backend.exceptionhandling.exceptions.GeneralException;
import com.wesdom.compa.backend.service.interfaces.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements IActivityService {

    @Autowired
    private IActivityRepository activityRepository;

    @Autowired
    private IProductionActivityRepository productionActivityRepository;

    @Override
    public void deleteActivity(Long id) {
        ProductionActivity productionActivity =
                productionActivityRepository.findTop1ByActivityId(id);
        if(productionActivity != null){
            throw new GeneralException(
                    ExceptionCodesEnum.ACTIVITY_IN_USE,
                    "La actividad esta en uso."
            );
        }else{
            activityRepository.delete(id);
        }
    }
}
