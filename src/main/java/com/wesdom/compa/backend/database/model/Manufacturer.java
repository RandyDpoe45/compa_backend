package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Manufacturer extends BaseUser {

    @JsonView({SystemViews.ManufacturerBasicView.class,SystemViews.GroupDetailedView.class
        ,SystemViews.EstateSegmentDetailView.class})
    private String plantation;

    @JsonView({SystemViews.ManufacturerBasicView.class,SystemViews.GroupDetailedView.class})
    private BigDecimal latitude;

    @JsonView({SystemViews.ManufacturerBasicView.class,SystemViews.GroupDetailedView.class})
    private BigDecimal longitude;

    @JsonView({SystemViews.ManufacturerBasicView.class})
    @ManyToMany(targetEntity = ManufacturerType.class, fetch = FetchType.LAZY)
    private List<ManufacturerType> type;

    //agregar tipo de id y id para todos los usuarios

}
