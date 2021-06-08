package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.ProductInStateSegment;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IProductInStateSegmentRepository {
    public ProductInStateSegment get(Long id);
    public Page<ProductInStateSegment> getAll(Map<String,String> params);
    public ProductInStateSegment create(ProductInStateSegment productInStateSegment);
    public ProductInStateSegment update(Long productId, ProductInStateSegment productInStateSegment);
    public void delete(Long productId);
    public List<ProductInStateSegment> getProductsByEstateSegmentId(Long estateSegmentId);
}
