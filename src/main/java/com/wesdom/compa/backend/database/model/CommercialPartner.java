package com.wesdom.compa.backend.database.model;

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
public class CommercialPartner extends BaseUser{

    @JsonView({SystemViews.CommercialPartnerBasicView.class})
    private String rut;

    @JsonView({SystemViews.CommercialPartnerBasicView.class})
    private String companyName;

}
