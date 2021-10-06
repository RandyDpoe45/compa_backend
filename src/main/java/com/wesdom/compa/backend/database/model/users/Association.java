package com.wesdom.compa.backend.database.model.users;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.database.model.AssociationMember;
import com.wesdom.compa.backend.database.model.AssociationPromoter;
import com.wesdom.compa.backend.database.model.Department;
import com.wesdom.compa.backend.database.model.Municipality;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

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
            SystemViews.RequestBasicView.class, SystemViews.AdvertisingBasicView.class,
            SystemViews.AdvertisingDetailView.class, SystemViews.RequestDetailView.class
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
            SystemViews.RequestBasicView.class, SystemViews.AdvertisingBasicView.class,
            SystemViews.AdvertisingDetailView.class, SystemViews.RequestDetailView.class
    })
    private String code;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestBasicView.class, SystemViews.AdvertisingBasicView.class,
            SystemViews.AdvertisingDetailView.class, SystemViews.RequestDetailView.class
    })
    private String name;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestDetailView.class, SystemViews.AdvertisingBasicView.class,
            SystemViews.AdvertisingDetailView.class
    })
    private String type;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestDetailView.class
    })
    private String rut;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestDetailView.class
    })
    private String camaraYComercio;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestDetailView.class
    })
    private String bank;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestDetailView.class
    })
    private String accountType;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestDetailView.class
    })
    private String accountNumber;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestDetailView.class, SystemViews.AdvertisingBasicView.class,
            SystemViews.AdvertisingDetailView.class
    })
    private String contactName;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestDetailView.class, SystemViews.AdvertisingBasicView.class,
            SystemViews.AdvertisingDetailView.class
    })
    private String contactPhone;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestDetailView.class, SystemViews.AdvertisingBasicView.class,
            SystemViews.AdvertisingDetailView.class
    })
    private String contactCharge;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestDetailView.class
    })
    private String docLegalRep;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.RequestDetailView.class, SystemViews.AdvertisingBasicView.class,
            SystemViews.AdvertisingDetailView.class
    })
    private String businessHours;

    @JsonView({
            SystemViews.AssociationDetailedView.class,SystemViews.AssociationBasicView.class,
            SystemViews.AdvertisingDetailView.class, SystemViews.AdvertisingBasicView.class
    })
    @Column(length = 600)
    private String introduction;

    @ManyToOne(targetEntity = Municipality.class,fetch = FetchType.EAGER)
    @JsonView({
            SystemViews.AssociationDetailedView.class, SystemViews.AdvertisingDetailView.class,
    })
    @NotFound(action = NotFoundAction.IGNORE)
    private Municipality municipality;

    @ManyToOne(targetEntity = Department.class,fetch = FetchType.LAZY)
    @JsonView({
            SystemViews.AssociationDetailedView.class, SystemViews.AdvertisingDetailView.class,
    })
    @NotFound(action = NotFoundAction.IGNORE)
    private Department department;

    @ManyToMany(targetEntity = AssociationMember.class, fetch = FetchType.LAZY, mappedBy = "association")
    private List<AssociationMember> associationMemberList;

    @ManyToMany(targetEntity = AssociationPromoter.class, fetch = FetchType.LAZY, mappedBy = "association")
    private List<AssociationPromoter> associationPromoterList;

    @JsonView({SystemViews.AdvertisingDetailView.class})
    public Integer getNumberOfMembers(){
        return this.associationMemberList.size();
    }
}
