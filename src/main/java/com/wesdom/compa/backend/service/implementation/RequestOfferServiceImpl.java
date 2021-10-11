package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.enums.RequestOffersStatusEnum;
import com.wesdom.compa.backend.database.model.RequestOffer;
import com.wesdom.compa.backend.database.model.RequestOfferStatus;
import com.wesdom.compa.backend.database.repositories.IRequestOfferRepository;
import com.wesdom.compa.backend.service.interfaces.INotificationService;
import com.wesdom.compa.backend.service.interfaces.IRequestOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestOfferServiceImpl implements IRequestOfferService {

    @Autowired
    private IRequestOfferRepository requestOfferRepository;

    @Autowired
    private INotificationService notificationService;

    @Override
    public RequestOffer update(Long id, RequestOffer requestOffer) {
        RequestOffer r = requestOfferRepository.get(id);
        //agregar manejo de deliverycode
        RequestOfferStatus s = r.getRequestOfferStatus();
        String deliveryCode = r.getDeliveryCode();
        requestOffer = requestOfferRepository.update(id,requestOffer);
        if(!s.getCode().equals(requestOffer.getRequestOfferStatus().getCode()) ){
            notificationService.createRequestOfferNotification(requestOffer);
        }
        if(requestOffer.getRequestOfferStatus().getCode().equals(RequestOffersStatusEnum.ACCEPTED.getCode())){
            notificationService.createRequestOfferCodeNotification(requestOffer);
        }

        return requestOffer;
    }
}
