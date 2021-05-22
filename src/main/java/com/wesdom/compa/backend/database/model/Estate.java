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
public class Estate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.EstateBasicView.class, SystemViews.EstateSegmentBasicView.class
            ,SystemViews.EstateSegmentDetailView.class})
    private Long id;

    @JsonView({SystemViews.EstateBasicView.class, SystemViews.EstateSegmentBasicView.class
            ,SystemViews.EstateSegmentDetailView.class})
    private String name;

    @ManyToOne(targetEntity = Department.class, fetch = FetchType.LAZY)
    @JsonView({SystemViews.EstateBasicView.class,SystemViews.EstateSegmentDetailView.class})
    private Department department;

    @ManyToOne(targetEntity = Municipality.class, fetch = FetchType.LAZY)
    @JsonView({SystemViews.EstateBasicView.class,SystemViews.EstateSegmentDetailView.class})
    private Municipality municipality;

    @JsonView({SystemViews.EstateBasicView.class,SystemViews.EstateSegmentDetailView.class})
    private String plantation;

    @JsonView({SystemViews.EstateBasicView.class,SystemViews.EstateSegmentDetailView.class})
    private BigDecimal latitude;

    @JsonView({SystemViews.EstateBasicView.class,SystemViews.EstateSegmentDetailView.class})
    private BigDecimal longitude;

    @JsonView({SystemViews.EstateBasicView.class,SystemViews.EstateSegmentDetailView.class})
    private BigDecimal totalArea;

    @JsonView({SystemViews.EstateBasicView.class,SystemViews.EstateSegmentDetailView.class})
    @ManyToOne(targetEntity = ManufacturerGroup.class, fetch = FetchType.LAZY)
    private ManufacturerGroup manufacturerGroup;
}
