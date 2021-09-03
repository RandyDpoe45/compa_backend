package com.wesdom.compa.backend.service.interfaces;

import com.wesdom.compa.backend.database.model.estatesegment.ProductInStateSegment;

public interface IProductInSegmentService {
    ProductInStateSegment createProductInSegment(ProductInStateSegment productInStateSegment);
    void deleteProductInStateSegment(Long id);
}
