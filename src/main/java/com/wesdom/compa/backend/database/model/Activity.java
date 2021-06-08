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
    @JsonView({SystemViews.ProductionActivityBasicView.class,SystemViews.ExpertVisitNoteBasicView.class})
    private Long id;

    @JsonView({SystemViews.ProductionActivityBasicView.class,SystemViews.ExpertVisitNoteBasicView.class})
    private String name;

    @JsonView({SystemViews.ProductionActivityBasicView.class,SystemViews.ExpertVisitNoteBasicView.class})
    private String description;

    @JsonView({SystemViews.ProductionActivityBasicView.class,SystemViews.ExpertVisitNoteBasicView.class})
    @ManyToOne(targetEntity = EstateSegmentType.class, fetch = FetchType.LAZY)
    private EstateSegmentType estateSegmentType;

    @JsonView({SystemViews.ProductionActivityBasicView.class,SystemViews.ExpertVisitNoteBasicView.class})
    private String stage;

    @ManyToMany(targetEntity = ActivityOption.class, fetch = FetchType.LAZY)
    private List<ActivityOption> activityOptionList;
}
