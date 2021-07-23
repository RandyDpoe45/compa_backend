package com.wesdom.compa.backend.database.model;

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
@NoArgsConstructor
@Accessors(chain = true)
public class Promoter extends BaseUser{

    @JsonView({
            SystemViews.PromoterBasicView.class,SystemViews.AssociationMemberBasicView.class,
            SystemViews.PromoterDetailedView.class
    })
    private String plantation;

    @JsonView({
            SystemViews.PromoterBasicView.class, SystemViews.PromoterDetailedView.class
    })
    private BigDecimal latitude;

    @JsonView({
            SystemViews.PromoterBasicView.class, SystemViews.PromoterDetailedView.class
    })
    private BigDecimal longitude;

    @JsonView({
            SystemViews.PromoterBasicView.class, SystemViews.PromoterDetailedView.class
    })
    @Column(length = 1000)
    private String introduction;

    @JsonView({
            SystemViews.PromoterBasicView.class, SystemViews.PromoterDetailedView.class
    })
    @ManyToMany(targetEntity = ManufacturerType.class, fetch = FetchType.LAZY)
    private List<ManufacturerType> type;

    @JsonView({
            SystemViews.PromoterBasicView.class, SystemViews.AssociationMemberBasicView.class,
            SystemViews.PromoterDetailedView.class
    })
    private String motorcycle;

    @JsonView({
            SystemViews.PromoterBasicView.class, SystemViews.PromoterDetailedView.class
    })
    private String availableDays;

    @JsonView({
            SystemViews.PromoterBasicView.class, SystemViews.PromoterDetailedView.class
    })
    private Boolean morning;

    @JsonView({
            SystemViews.PromoterBasicView.class, SystemViews.PromoterDetailedView.class
    })
    private Boolean afternoon;

    @JsonView({
            SystemViews.PromoterBasicView.class, SystemViews.PromoterDetailedView.class
    })
    @ManyToMany(targetEntity = ManufacturerGroup.class, fetch = FetchType.LAZY)
    private List<ManufacturerGroup> manufacturerGroupList;
}
