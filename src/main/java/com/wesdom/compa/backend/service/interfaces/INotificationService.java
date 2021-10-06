package com.wesdom.compa.backend.service.interfaces;

import com.wesdom.compa.backend.database.model.*;
import com.wesdom.compa.backend.database.model.estatesegment.EstateSegment;
import com.wesdom.compa.backend.database.model.users.Manufacturer;

public interface INotificationService {

    void createManufacturerGroupNotification(AssociationMember associationMember);
    void createRequestNotification(Request request);
    void createRequestOfferNotification(RequestOffer requestOffer);
    void createRequestOfferCodeNotification(RequestOffer requestOffer);
    void createExpertAlertNotification(ExpertAlert expertAlert);
    void createExpertVisitTaskNotification(ExpertVisit expertVisit);
    void createMGEstateSegmentNotification(Long estateSegmentId, Long manufacturerId);
    void createMGManufacturerNotification(Long manufacturerGroupId, Long manufacturerId);
    void createProductionFinishedNotification(
            Long productInStateSegmentId,
            Long promoterId,
            Long associationId
            );
}
