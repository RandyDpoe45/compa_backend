package com.wesdom.compa.backend.database.model.users;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Association{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestBasicView.class, SystemViews.AdvertisingBasicView.class
    })
    private Long id;

    @OneToOne(targetEntity = AuthUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    @JsonView({
            SystemViews.AssociationBasicView.class, SystemViews.AssociationDetailedView.class
    })
    @NotFound(action = NotFoundAction.IGNORE)
    private AuthUser authUser;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestBasicView.class, SystemViews.AdvertisingBasicView.class
    })
    private String code;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestBasicView.class, SystemViews.AdvertisingBasicView.class
    })
    private String name;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestBasicView.class, SystemViews.AdvertisingBasicView.class
    })
    private String type;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestBasicView.class, SystemViews.AdvertisingBasicView.class
    })
    private String rut;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestBasicView.class
    })
    private String camaraYComercio;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestBasicView.class
    })
    private String bank;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestBasicView.class
    })
    private String accountType;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestBasicView.class
    })
    private String accountNumber;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestBasicView.class, SystemViews.AdvertisingBasicView.class
    })
    private String contactName;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestBasicView.class
    })
    private String docLegalRep;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestBasicView.class, SystemViews.AdvertisingBasicView.class
    })
    private String businessHours;

}
