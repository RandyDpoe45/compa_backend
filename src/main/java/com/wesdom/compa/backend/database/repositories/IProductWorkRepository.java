package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.estatesegment.ProductWork;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IProductWorkRepository {
    public ProductWork get(Long id);
    public Page<ProductWork> getAll(Map<String,String> params);
    public ProductWork save(ProductWork product);
    public ProductWork update(Long productId, ProductWork product);
    public void delete(Long productId);
}
