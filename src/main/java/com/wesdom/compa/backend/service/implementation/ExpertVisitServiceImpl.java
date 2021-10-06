package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.model.ExpertVisit;
import com.wesdom.compa.backend.database.repositories.IExpertVisitRepository;
import com.wesdom.compa.backend.service.interfaces.IExpertVisitService;
import com.wesdom.compa.backend.service.interfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpertVisitServiceImpl implements IExpertVisitService {

    @Autowired
    private IExpertVisitRepository expertVisitRepository;

    @Autowired
    private INotificationService notificationService;

    @Override
    public ExpertVisit update(Long id, ExpertVisit expertVisit) {
        expertVisit = expertVisitRepository.update(id,expertVisit);
        if(expertVisit.getFutureTask() != null){
            notificationService.createExpertVisitTaskNotification(expertVisit);
        }
        return expertVisit;
    }
}
