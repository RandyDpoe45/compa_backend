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
public class ProductionActivityAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.ProductionActivityAnswerBasicView.class})
    private Long id;

    @ManyToOne(targetEntity = ProductionActivity.class,fetch = FetchType.LAZY)
    private ProductionActivity productionActivity;

    @JsonView({SystemViews.ProductionActivityAnswerBasicView.class})
    @ManyToOne(targetEntity = ActivityOption.class, fetch = FetchType.LAZY)
    private ActivityOption activityOption;

    @JsonView({SystemViews.ProductionActivityAnswerBasicView.class})
    private String answer;
}
