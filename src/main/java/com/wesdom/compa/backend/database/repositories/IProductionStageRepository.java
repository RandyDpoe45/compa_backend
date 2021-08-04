package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.ProductionStage;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IProductionStageRepository {
    public ProductionStage get(Long id);
    public Page<ProductionStage> getAll(Map<String,String> params);
    public ProductionStage save(ProductionStage productionStage);
    public ProductionStage update(Long productionStageId, ProductionStage productionStage);
    public ProductionStage getLastStageByEstateSegmentTypeId(Long estateSegmentTypeId);
    public void delete(Long productionStageId);
    public ProductionStage getByStageOrderAndEstateSegmentTypeId(Long stageOrder, Long estateSegmentTypeId);
}
