package com.wesdom.compa.backend.database.model.users;

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
@Accessors(chain = true)
@NoArgsConstructor
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({
            SystemViews.AuthUserBasicView.class,SystemViews.ManufacturerBasicView.class,
            SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class,
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.PromoterDetailedView.class
    })
    private Long id;

    @JsonView({
            SystemViews.AuthUserBasicView.class,SystemViews.ManufacturerBasicView.class,
            SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class,
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.PromoterDetailedView.class
    })
    private String username;

    @JsonView({
            SystemViews.AuthUserBasicView.class,SystemViews.ManufacturerBasicView.class,
            SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class,
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.PromoterDetailedView.class
    })
    private String email;

    @JsonView({
            SystemViews.AuthUserBasicView.class,SystemViews.ManufacturerBasicView.class,
            SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class,
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.PromoterDetailedView.class
    })
    private String type;

    @JsonView({
            SystemViews.AuthUserBasicView.class,SystemViews.ManufacturerBasicView.class,
            SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class,
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.PromoterDetailedView.class
    })
    private Boolean isActive = true;

    @Column(length = 600)
    private String password;



}
