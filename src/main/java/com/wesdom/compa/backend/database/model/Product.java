package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ExpertVisitBasicView.class})
    private Long id;

    @JsonView({SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ExpertVisitBasicView.class})
    private String code;

    @JsonView({SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ExpertVisitBasicView.class})
    private String name;

    @JsonView({SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,})
    private BigDecimal avProductivity;

    @JsonView({SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class})
    private String metricUnit;

    @JsonView({SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class})
    private String species;

}

