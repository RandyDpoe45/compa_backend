package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.RequestOffer;
import com.wesdom.compa.backend.database.model.RequestOfferStatus;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IRequestOfferRepository {
    RequestOffer get(Long id);
    public Page<RequestOffer> getAll(Map<String,String> params);
    public RequestOffer save(RequestOffer requestOffer);
    public List<RequestOfferStatus> getStatusList();
    public RequestOfferStatus getStatusByCode(String id);
    public RequestOffer update(Long requestOfferId, RequestOffer requestOffer);
    public void delete(Long requestOfferId);
}
