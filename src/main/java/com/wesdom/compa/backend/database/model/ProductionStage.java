package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.database.model.estatesegment.EstateSegmentType;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ProductionStage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({
            SystemViews.ActivityBasicView.class, SystemViews.ProductionStageBasicView.class,
            SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ExpertVisitBasicView.class,SystemViews.ProductWorkBasicView.class
    })
    private Long id;

    @JsonView({
            SystemViews.ActivityBasicView.class, SystemViews.ProductionStageBasicView.class,
            SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ExpertVisitBasicView.class, SystemViews.ProductWorkBasicView.class
    })
    private Long stageOrder = 1l;

    @JsonView({SystemViews.ProductionStageBasicView.class})
    @ManyToOne(targetEntity = EstateSegmentType.class, fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private EstateSegmentType estateSegmentType;

    @JsonView({
            SystemViews.ActivityBasicView.class, SystemViews.ProductionStageBasicView.class,
            SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ExpertVisitBasicView.class, SystemViews.ProductWorkBasicView.class
    })
    private String name;
}
