package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.model.ExpertAlert;
import com.wesdom.compa.backend.database.model.ExpertVisit;
import com.wesdom.compa.backend.database.repositories.IExpertAlertRepository;
import com.wesdom.compa.backend.database.repositories.IExpertVisitRepository;
import com.wesdom.compa.backend.service.interfaces.IExpertAlertService;
import com.wesdom.compa.backend.service.interfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpertAlertServiceImpl implements IExpertAlertService {

    @Autowired
    private IExpertAlertRepository expertAlertRepository;

    @Autowired
    private INotificationService notificationService;

    @Override
    public ExpertAlert create(ExpertAlert expertAlert) {
        expertAlert = expertAlertRepository.save(expertAlert);
        if(expertAlert != null){
            notificationService.createExpertAlertNotification(expertAlert);
        }
        return expertAlert;
    }
}
