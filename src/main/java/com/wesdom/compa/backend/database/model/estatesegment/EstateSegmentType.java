package com.wesdom.compa.backend.database.model.estatesegment;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class EstateSegmentType {

    @Id
    @JsonView({SystemViews.ManufacturerBasicView.class, SystemViews.PromoterBasicView.class, SystemViews.EstateSegmentBasicView.class
            , SystemViews.EstateSegmentDetailView.class, SystemViews.ProductionActivityBasicView.class,
            SystemViews.ActivityBasicView.class, SystemViews.ProductionStageBasicView.class})
    private Long id;

    @JsonView({SystemViews.ManufacturerBasicView.class, SystemViews.PromoterBasicView.class, SystemViews.EstateSegmentBasicView.class
            , SystemViews.EstateSegmentDetailView.class, SystemViews.ProductionActivityBasicView.class,
            SystemViews.ActivityBasicView.class, SystemViews.ProductionStageBasicView.class})
    private String code;

    @JsonView({SystemViews.ManufacturerBasicView.class, SystemViews.PromoterBasicView.class, SystemViews.EstateSegmentBasicView.class
            , SystemViews.EstateSegmentDetailView.class, SystemViews.ProductionActivityBasicView.class,
            SystemViews.ActivityBasicView.class, SystemViews.ProductionStageBasicView.class})
    private String name;
}
