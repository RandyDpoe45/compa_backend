package com.wesdom.compa.backend.service.interfaces;

import com.wesdom.compa.backend.database.model.ProductionStage;
import com.wesdom.compa.backend.dtos.ProductionStageOrderDto;

public interface IProductionStageService {
     ProductionStage save(ProductionStage productionStage);
     ProductionStage updateOrder(ProductionStageOrderDto productionStageOrderDto);
     void deleteProductionStage(Long id);
}
