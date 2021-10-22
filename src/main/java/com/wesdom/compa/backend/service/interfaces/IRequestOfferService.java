package com.wesdom.compa.backend.service.interfaces;

import com.wesdom.compa.backend.database.model.RequestOffer;

public interface IRequestOfferService {

    RequestOffer save(RequestOffer requestOffer);
    RequestOffer update(Long id, RequestOffer requestOffer);
}
