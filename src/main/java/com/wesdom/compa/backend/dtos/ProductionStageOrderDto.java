package com.wesdom.compa.backend.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductionStageOrderDto {

    private Long productionStageId;
    private Long desiredStageOrder;
    private Long currentStageOrder;
}
