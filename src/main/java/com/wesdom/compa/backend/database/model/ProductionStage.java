package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ProductionStage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.ActivityBasicView.class, SystemViews.ProductionStageBasicView.class})
    private Long id;


    @JsonView({SystemViews.ProductionStageBasicView.class})
    @ManyToOne(targetEntity = EstateSegmentType.class, fetch = FetchType.LAZY)
    private EstateSegmentType estateSegmentType;

    @JsonView({SystemViews.ActivityBasicView.class, SystemViews.ProductionStageBasicView.class})
    private String name;
}
