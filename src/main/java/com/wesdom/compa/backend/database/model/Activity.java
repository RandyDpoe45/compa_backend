package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({SystemViews.ProductionActivityBasicView.class,SystemViews.ExpertVisitNoteBasicView.class,
            SystemViews.ActivityBasicView.class})
    private Long id;

    @JsonView({SystemViews.ProductionActivityBasicView.class,SystemViews.ExpertVisitNoteBasicView.class,
            SystemViews.ActivityBasicView.class})
    private String name;

    @JsonView({SystemViews.ProductionActivityBasicView.class,SystemViews.ExpertVisitNoteBasicView.class,
            SystemViews.ActivityBasicView.class})
    private String description;

    @JsonView({SystemViews.ProductionActivityBasicView.class,SystemViews.ExpertVisitNoteBasicView.class,
            SystemViews.ActivityBasicView.class})
    @ManyToOne(targetEntity = EstateSegmentType.class, fetch = FetchType.LAZY)
    private EstateSegmentType estateSegmentType;

    @JsonView({SystemViews.ProductionActivityBasicView.class,SystemViews.ExpertVisitNoteBasicView.class,
            SystemViews.ActivityBasicView.class})
    @ManyToOne(targetEntity = ProductionStage.class, fetch = FetchType.LAZY)
    private ProductionStage productionStage;

    @OneToMany(targetEntity = ActivityOption.class, fetch = FetchType.LAZY, mappedBy = "activity")
    private List<ActivityOption> activityOptionList;
}
