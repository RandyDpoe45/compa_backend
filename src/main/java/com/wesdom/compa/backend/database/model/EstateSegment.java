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
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
@NoArgsConstructor
@Accessors(chain = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "SegmentType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AgriculturalSegment.class, name = "AgriculturalSegment"),
        @JsonSubTypes.Type(value = BeekeepingSegment.class, name = "BeekeepingSegment")
})
public class EstateSegment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.EstateSegmentBasicView.class,SystemViews.ProductInStateBasicView.class
            ,SystemViews.EstateSegmentDetailView.class})
    private Long id;

    @JsonView({SystemViews.EstateSegmentBasicView.class,SystemViews.ProductInStateBasicView.class
            ,SystemViews.EstateSegmentDetailView.class})
    private String code;

    @JsonView({SystemViews.EstateSegmentBasicView.class,SystemViews.EstateSegmentDetailView.class})
    @ManyToOne(targetEntity = Estate.class,fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Estate estate;

    @ManyToOne(targetEntity = EstateSegmentType.class, fetch = FetchType.LAZY)
    @JsonView({SystemViews.EstateSegmentBasicView.class,SystemViews.EstateSegmentDetailView.class})
    private EstateSegmentType estateSegmentType;

    @JsonView({SystemViews.EstateSegmentBasicView.class,SystemViews.ProductInStateBasicView.class
            ,SystemViews.EstateSegmentDetailView.class})
    private BigDecimal area;

    @JsonView({SystemViews.EstateSegmentBasicView.class,SystemViews.ProductInStateBasicView.class
            ,SystemViews.EstateSegmentDetailView.class})
    private String contactName;

    @JsonView({SystemViews.EstateSegmentBasicView.class,SystemViews.ProductInStateBasicView.class
            ,SystemViews.EstateSegmentDetailView.class})
    private String contactPhone;

    @JsonView({SystemViews.EstateSegmentDetailView.class})
    @ManyToMany(targetEntity = Manufacturer.class, fetch = FetchType.LAZY)
    private List<Manufacturer> manufacturerList;

}
