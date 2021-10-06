package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.database.model.ClassificationScore;
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
public class ClassificationScoreDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.ClassificationScoreDescriptionBasicView.class})
    private Long id;

    @JsonView({SystemViews.ClassificationScoreDescriptionBasicView.class})
    private String entityName;

    @JsonView({SystemViews.ClassificationScoreDescriptionBasicView.class})
    @ManyToOne(targetEntity = ClassificationScore.class, fetch = FetchType.LAZY)
    private ClassificationScore classificationScore;

    @Column(length = 700)
    @JsonView({SystemViews.ClassificationScoreDescriptionBasicView.class})
    private String description;
}
