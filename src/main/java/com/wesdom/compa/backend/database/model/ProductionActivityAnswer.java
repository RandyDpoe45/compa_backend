package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ProductionActivityAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.ProductionActivityAnswerBasicView.class})
    private Long id;

    @ManyToOne(targetEntity = ProductionActivity.class,fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProductionActivity productionActivity;

    @JsonView({SystemViews.ProductionActivityAnswerBasicView.class})
    @ManyToOne(targetEntity = ActivityOption.class, fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ActivityOption activityOption;

    @JsonView({SystemViews.ProductionActivityAnswerBasicView.class})
    private String answer;
}
