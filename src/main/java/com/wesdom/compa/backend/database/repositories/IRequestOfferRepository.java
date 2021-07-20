package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.RequestOffer;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IRequestOfferRepository {
    public RequestOffer get(Long id);
    public Page<RequestOffer> getAll(Map<String,String> params);
    public RequestOffer create(RequestOffer requestOffer);
    public RequestOffer update(Long requestOfferId, RequestOffer requestOffer);
    public void delete(Long requestOfferId);
}
