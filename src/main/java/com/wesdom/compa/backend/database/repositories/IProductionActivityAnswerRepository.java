package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.activity.ProductionActivityAnswer;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IProductionActivityAnswerRepository {
    public ProductionActivityAnswer get(Long id);
    public Page<ProductionActivityAnswer> getAll(Map<String,String> params);
    public ProductionActivityAnswer save(ProductionActivityAnswer activity);
    public ProductionActivityAnswer update(Long activityId, ProductionActivityAnswer activity);
    public void delete(Long activityId);
}
