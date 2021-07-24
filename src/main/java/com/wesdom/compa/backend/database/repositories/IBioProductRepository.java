package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.BioProduct;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IBioProductRepository {
    public BioProduct get(Long id);
    public Page<BioProduct> getAll(Map<String,String> params);
    public BioProduct create(BioProduct product);
    public BioProduct update(Long productId, BioProduct product);
    public void delete(Long productId);
    public List<BioProduct> findAllById(List<Long> ids);
}
