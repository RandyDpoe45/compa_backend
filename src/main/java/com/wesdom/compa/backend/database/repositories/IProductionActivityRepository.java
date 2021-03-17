package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.ProductionActivity;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IProductionActivityRepository {
    public ProductionActivity get(Long id);
    public Page<ProductionActivity> getAll(Map<String,String> params);
    public ProductionActivity create(ProductionActivity activity);
    public ProductionActivity update(Long activityId, ProductionActivity activity);
    public void delete(Long activityId);
}
