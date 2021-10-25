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
    public RequestOffer save(RequestOffer requestOffer) {
        RequestOfferStatus requestOfferStatus = requestOfferRepository.getStatusByCode(
                RequestOffersStatusEnum.VERIFYING.getCode()
        );
        requestOffer.setRequestOfferStatus(requestOfferStatus);
        return requestOfferRepository.save(requestOffer);
    }

    @Override
    public RequestOffer update(Long id, RequestOffer requestOffer) {
        RequestOffer r = requestOfferRepository.get(id);
        //agregar manejo de deliverycode
        RequestOfferStatus s = r.getRequestOfferStatus();

        requestOffer = requestOfferRepository.update(id, requestOffer);
        if (s != null && !s.getCode().equals(requestOffer.getRequestOfferStatus().getCode())) {
            notificationService.createRequestOfferNotification(requestOffer);
            if (requestOffer.getRequestOfferStatus().getCode().equals(RequestOffersStatusEnum.ACCEPTED.getCode())) {
                String deliveryCode = generateCode(requestOffer);
                notificationService.createRequestOfferCodeNotification(requestOffer);
                requestOffer.setDeliveryCode(deliveryCode);
                requestOffer = requestOfferRepository.update(id, requestOffer);
            }
        }

        return requestOffer;
    }

    private String generateCode(RequestOffer requestOffer) {
        String code = String.format(
                "%s-%d-%d-%d-%d",
                requestOffer.getRequest().getAssociation().getCode(),
                requestOffer.getManufacturerGroup().getId(),
                requestOffer.getProductInStateSegmentList().get(0).getEstateSegment().getId(),
                requestOffer.getRequest().getProduct().getId(),
                requestOffer.getId()
        );
        return code;
    }
}
