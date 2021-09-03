package com.wesdom.compa.backend.database.model.activity;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.database.model.estatesegment.EstateSegmentType;
import com.wesdom.compa.backend.database.model.ProductionStage;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
    @JsonView({
            SystemViews.ProductionActivityBasicView.class,SystemViews.ExpertVisitNoteBasicView.class,
            SystemViews.ActivityBasicView.class
    })
    private Long id;

    @JsonView({
            SystemViews.ProductionActivityBasicView.class,SystemViews.ExpertVisitNoteBasicView.class,
            SystemViews.ActivityBasicView.class
    })
    private String name;

    @JsonView({
            SystemViews.ProductionActivityBasicView.class,SystemViews.ExpertVisitNoteBasicView.class,
            SystemViews.ActivityBasicView.class
    })
    private String description;

    @JsonView({
            SystemViews.ProductionActivityBasicView.class,SystemViews.ExpertVisitNoteBasicView.class,
            SystemViews.ActivityBasicView.class
    })
    private String answerType;

    @JsonView({
            SystemViews.ProductionActivityBasicView.class,SystemViews.ExpertVisitNoteBasicView.class,
            SystemViews.ActivityBasicView.class
    })
    @ManyToOne(targetEntity = EstateSegmentType.class, fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private EstateSegmentType estateSegmentType;

    @JsonView({
            SystemViews.ProductionActivityBasicView.class,SystemViews.ExpertVisitNoteBasicView.class,
            SystemViews.ActivityBasicView.class
    })
    @ManyToOne(targetEntity = ProductionStage.class, fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private ProductionStage productionStage;

    @JsonView({SystemViews.ActivityBasicView.class})
    @OneToMany(targetEntity = ActivityOption.class, fetch = FetchType.LAZY, mappedBy = "activity")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<ActivityOption> activityOptionList;
}
