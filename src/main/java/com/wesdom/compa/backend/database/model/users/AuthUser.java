package com.wesdom.compa.backend.database.model.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

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
            SystemViews.PromoterDetailedView.class, SystemViews.CommercialPartnerDetailView.class
    })
    private Long id;

    @JsonView({
            SystemViews.AuthUserBasicView.class,SystemViews.ManufacturerBasicView.class,
            SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class,
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.PromoterDetailedView.class, SystemViews.CommercialPartnerDetailView.class
    })
    private String username;

    @JsonView({
            SystemViews.AuthUserBasicView.class,SystemViews.ManufacturerBasicView.class,
            SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class,
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.PromoterDetailedView.class, SystemViews.CommercialPartnerDetailView.class
    })
    private String email;

    @JsonView({
            SystemViews.AuthUserBasicView.class,SystemViews.ManufacturerBasicView.class,
            SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class,
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.PromoterDetailedView.class, SystemViews.CommercialPartnerDetailView.class
    })
    private String type;

    @JsonView({
            SystemViews.AuthUserBasicView.class,SystemViews.ManufacturerBasicView.class,
            SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class,
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.PromoterDetailedView.class, SystemViews.CommercialPartnerDetailView.class
    })
    private Boolean isActive = true;

    @Column(length = 600)
    private String password;

    @JsonView({
            SystemViews.AuthUserBasicView.class,SystemViews.ManufacturerBasicView.class,
            SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class,
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.PromoterDetailedView.class, SystemViews.CommercialPartnerDetailView.class
    })
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate registryDate;

}
