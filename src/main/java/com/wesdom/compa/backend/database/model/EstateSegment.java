package com.wesdom.compa.backend.database.model;

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
public class EstateSegment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.EstateSegmentBasicView.class,SystemViews.ProductInStateBasicView.class})
    private Long id;

    @JsonView({SystemViews.EstateSegmentBasicView.class,SystemViews.ProductInStateBasicView.class})
    private String code;

    @JsonView({SystemViews.EstateSegmentBasicView.class})
    @ManyToOne(targetEntity = Estate.class,fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Estate estate;

    @ManyToOne(targetEntity = EstateSegmentType.class, fetch = FetchType.LAZY)
    @JsonView({SystemViews.EstateSegmentBasicView.class})
    private EstateSegmentType estateSegmentType;

    @JsonView({SystemViews.EstateSegmentBasicView.class,SystemViews.ProductInStateBasicView.class})
    private BigDecimal area;

}
