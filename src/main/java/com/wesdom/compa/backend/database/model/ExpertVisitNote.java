package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonView;
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
public class ExpertVisitNote {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    @JsonView({SystemViews.ExpertVisitNoteBasicView.class})
    private Long id;

    @JsonView({SystemViews.ExpertVisitNoteBasicView.class})
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(targetEntity = ExpertVisit.class,fetch = FetchType.LAZY)
    private ExpertVisit expertVisit;

    @JsonView({SystemViews.ExpertVisitNoteBasicView.class})
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(targetEntity = ProductionActivity.class,fetch = FetchType.LAZY)
    private ProductionActivity productionActivity;

    @JsonView({SystemViews.ExpertVisitNoteBasicView.class})
    @Column(length = 600)
    private String comment;

}
