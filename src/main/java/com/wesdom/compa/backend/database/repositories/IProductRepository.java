package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.Product;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IProductRepository {
    public Product get(Long id);
    public Page<Product> getAll(Map<String,String> params);
    public Product create(Product product);
    public Product update(Long productId, Product product);
    public void delete(Long productId);
}
