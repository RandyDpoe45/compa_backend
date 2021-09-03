package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.users.CommercialPartner;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ICommercialPartnerRepository {
    public CommercialPartner get(Long id);
    public Page<CommercialPartner> getAll(Map<String,String> params);
    public CommercialPartner save(CommercialPartner comercialPartner);
    public CommercialPartner update(Long comercialPartnerId, CommercialPartner comercialPartner);
    public void delete(Long comercialPartnerId);
}
