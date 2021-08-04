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
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ManufacturerGroup {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    @JsonView({
            SystemViews.GroupBasicView.class,SystemViews.GroupDetailedView.class,
            SystemViews.EstateBasicView.class ,SystemViews.EstateSegmentDetailView.class,
            SystemViews.RequestOfferBasicView.class, SystemViews.AssociationMemberBasicView.class,
            SystemViews.PromoterDetailedView.class, SystemViews.AdvertisingBasicView.class,
            SystemViews.RequestOfferDetailView.class
    })
    private Long id;

    @JsonView({
            SystemViews.GroupBasicView.class, SystemViews.GroupDetailedView.class,
            SystemViews.EstateBasicView.class, SystemViews.EstateSegmentDetailView.class,
            SystemViews.RequestOfferBasicView.class, SystemViews.AssociationMemberBasicView.class,
            SystemViews.PromoterDetailedView.class, SystemViews.AdvertisingBasicView.class,
            SystemViews.RequestOfferDetailView.class
    })
    private String name;

    @JsonView({
            SystemViews.GroupBasicView.class,SystemViews.GroupDetailedView.class,
            SystemViews.EstateBasicView.class,SystemViews.EstateSegmentDetailView.class,
            SystemViews.RequestOfferBasicView.class,SystemViews.AssociationMemberBasicView.class,
            SystemViews.PromoterDetailedView.class, SystemViews.AdvertisingBasicView.class
    })
    @Column(length = 1000)
    private String history;

    @JsonView({
            SystemViews.GroupBasicView.class,SystemViews.GroupDetailedView.class,
            SystemViews.RequestOfferBasicView.class,SystemViews.AssociationMemberBasicView.class,
            SystemViews.PromoterDetailedView.class
    })
    @ManyToOne(targetEntity = GroupType.class, fetch = FetchType.LAZY)
    private GroupType groupType;

    @OneToMany(targetEntity = Estate.class, mappedBy = "manufacturerGroup",fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Estate> estateList;

    @JsonView({SystemViews.GroupDetailedView.class})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToMany(targetEntity = Manufacturer.class, fetch = FetchType.LAZY)
    private List<Manufacturer> manufacturerList;
}
