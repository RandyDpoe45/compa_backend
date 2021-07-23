package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class GroupType {

    @Id
    @JsonView({
            SystemViews.GroupBasicView.class,SystemViews.GroupDetailedView.class,
            SystemViews.RequestOfferBasicView.class,SystemViews.AssociationMemberBasicView.class,
            SystemViews.PromoterDetailedView.class
    })
    private Long id;

    @JsonView({
            SystemViews.GroupBasicView.class,SystemViews.GroupDetailedView.class,
            SystemViews.RequestOfferBasicView.class,SystemViews.AssociationMemberBasicView.class,
            SystemViews.PromoterDetailedView.class
    })
    private String code;

    @JsonView({
            SystemViews.GroupBasicView.class,SystemViews.GroupDetailedView.class,
            SystemViews.RequestOfferBasicView.class,SystemViews.AssociationMemberBasicView.class,
            SystemViews.PromoterDetailedView.class
    })
    private String name;
}
