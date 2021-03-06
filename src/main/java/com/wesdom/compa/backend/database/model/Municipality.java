package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Municipality {

    @Id
    @JsonView({SystemViews.ManufacturerBasicView.class,  SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class})
    private Long id;

    @JsonView({SystemViews.ManufacturerBasicView.class,  SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class})
    private String code;

    @JsonView({SystemViews.ManufacturerBasicView.class,  SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class})
    private String name;

    @ManyToOne(targetEntity = Department.class, fetch = FetchType.LAZY)
    private Department department;
}
