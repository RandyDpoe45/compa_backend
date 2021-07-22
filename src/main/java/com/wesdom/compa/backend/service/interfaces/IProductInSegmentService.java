package com.wesdom.compa.backend.service.interfaces;

import com.wesdom.compa.backend.database.model.ProductInStateSegment;

public interface IProductInSegmentService {
    ProductInStateSegment createProductInSegment(ProductInStateSegment productInStateSegment);
    void deleteProductInStateSegment(Long id);
}
