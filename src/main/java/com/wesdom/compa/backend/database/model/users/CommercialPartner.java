package com.wesdom.compa.backend.database.model.users;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CommercialPartner extends BaseUser {

    @JsonView({
            SystemViews.CommercialPartnerBasicView.class, SystemViews.RequestBasicView.class,
            SystemViews.RequestOfferDetailView.class, SystemViews.RequestDetailView.class,
            SystemViews.CommercialPartnerDetailView.class
    })
    private String rut;

    @JsonView({
            SystemViews.CommercialPartnerBasicView.class, SystemViews.RequestBasicView.class,
            SystemViews.RequestOfferDetailView.class, SystemViews.RequestDetailView.class,
            SystemViews.CommercialPartnerDetailView.class
    })
    private String companyName;

    @JsonView({SystemViews.CommercialPartnerDetailView.class})
    private String camaraYComercio;

    @JsonView({SystemViews.CommercialPartnerDetailView.class})
    private String bank;

    @JsonView({SystemViews.CommercialPartnerDetailView.class})
    private String accountType;

    @JsonView({SystemViews.CommercialPartnerDetailView.class})
    private String accountNumber;

    @JsonView({SystemViews.CommercialPartnerDetailView.class})
    private String personType;

}
