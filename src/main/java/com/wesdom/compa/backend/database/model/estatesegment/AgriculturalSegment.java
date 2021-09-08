package com.wesdom.compa.backend.database.model.estatesegment;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@DiscriminatorValue("A")
@NoArgsConstructor
@Accessors(chain = true)
@JsonTypeName("AgriculturalSegment")
public class AgriculturalSegment extends EstateSegment{

    @JsonView({
            SystemViews.EstateSegmentBasicView.class,SystemViews.ProductInStateBasicView.class,
            SystemViews.EstateSegmentDetailView.class
    })
    private BigDecimal promHarvest;

    @JsonView({
            SystemViews.EstateSegmentBasicView.class,SystemViews.ProductInStateBasicView.class,
            SystemViews.EstateSegmentDetailView.class
    })
    private BigDecimal ph;

    @JsonView({
            SystemViews.EstateSegmentBasicView.class,SystemViews.ProductInStateBasicView.class,
            SystemViews.EstateSegmentDetailView.class
    })
    private String soilMinerals;

    @JsonView({
            SystemViews.EstateSegmentBasicView.class,SystemViews.ProductInStateBasicView.class,
            SystemViews.EstateSegmentDetailView.class
    })
    private String lateUse;
}
