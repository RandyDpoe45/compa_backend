package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.util.Lazy;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.ManufacturerBasicView.class, SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class,
            SystemViews.GroupDetailedView.class,SystemViews.EstateSegmentDetailView.class,SystemViews.ExpertVisitBasicView.class})
    private Long id;

    @OneToOne(targetEntity = AuthUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    @JsonView({SystemViews.ManufacturerBasicView.class, SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class})
    private AuthUser authUser;

    @JsonView({SystemViews.ManufacturerBasicView.class, SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class,
            SystemViews.GroupDetailedView.class,SystemViews.EstateSegmentDetailView.class,SystemViews.ExpertVisitBasicView.class})
    private String firstName;

    @JsonView({SystemViews.ManufacturerBasicView.class,  SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class,
            SystemViews.GroupDetailedView.class,SystemViews.EstateSegmentDetailView.class,SystemViews.ExpertVisitBasicView.class})
    private String secondName;

    @JsonView({SystemViews.ManufacturerBasicView.class,  SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class,
            SystemViews.GroupDetailedView.class,SystemViews.EstateSegmentDetailView.class,SystemViews.ExpertVisitBasicView.class})
    private String firstLastname;

    @JsonView({SystemViews.ManufacturerBasicView.class,  SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class,
            SystemViews.GroupDetailedView.class,SystemViews.EstateSegmentDetailView.class,SystemViews.ExpertVisitBasicView.class})
    private String secondLastName;

    @JsonView({SystemViews.ManufacturerBasicView.class,  SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class,
            SystemViews.GroupDetailedView.class,SystemViews.EstateSegmentDetailView.class,SystemViews.ExpertVisitBasicView.class})
    private String phone;

    @JsonView({SystemViews.ManufacturerBasicView.class,  SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class,
            SystemViews.GroupDetailedView.class})
    private String gender;

    @JsonView({SystemViews.ManufacturerBasicView.class,  SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class,
            SystemViews.GroupDetailedView.class,SystemViews.EstateSegmentDetailView.class})
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;

    @ManyToOne(targetEntity = Municipality.class,fetch = FetchType.EAGER)
    @JsonView({SystemViews.ManufacturerBasicView.class,  SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class})
    private Municipality municipality;

    @ManyToOne(targetEntity = Department.class,fetch = FetchType.LAZY)
    @JsonView({SystemViews.ManufacturerBasicView.class,  SystemViews.PromoterBasicView.class,SystemViews.CommercialPartnerBasicView.class})
    private Department department;


}
