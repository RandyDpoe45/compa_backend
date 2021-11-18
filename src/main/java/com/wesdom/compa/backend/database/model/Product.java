package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({
            SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ExpertVisitBasicView.class, SystemViews.ProductBasicView.class,
            SystemViews.RequestBasicView.class, SystemViews.AdvertisingBasicView.class,
            SystemViews.AdvertisingDetailView.class, SystemViews.RequestDetailView.class,
            SystemViews.RequestOfferBasicView.class
    })
    private Long id;

    @JsonView({
            SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ExpertVisitBasicView.class, SystemViews.ProductBasicView.class,
            SystemViews.RequestBasicView.class, SystemViews.AdvertisingBasicView.class,
            SystemViews.AdvertisingDetailView.class, SystemViews.RequestDetailView.class,
            SystemViews.RequestOfferBasicView.class
    })
    private String code;

    @JsonView({
            SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ExpertVisitBasicView.class, SystemViews.ProductBasicView.class,
            SystemViews.AdvertisingDetailView.class,SystemViews.RequestOfferBasicView.class
    })
    @ManyToOne(targetEntity = ProductType.class, fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private ProductType productType;

    @JsonView({
            SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ExpertVisitBasicView.class, SystemViews.ProductBasicView.class,
            SystemViews.RequestBasicView.class,SystemViews.AdvertisingBasicView.class,
            SystemViews.AdvertisingDetailView.class, SystemViews.RequestDetailView.class,
            SystemViews.RequestOfferBasicView.class
    })
    private String name;

    @JsonView({
            SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ProductBasicView.class
    })
    private BigDecimal avProductivity;

    @JsonView({
            SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ProductBasicView.class
    })
    @ManyToOne(targetEntity = MeasureUnit.class, fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private MeasureUnit measureUnit;

    @JsonView({
            SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ProductBasicView.class, SystemViews.RequestBasicView.class,
            SystemViews.AdvertisingDetailView.class, SystemViews.RequestDetailView.class
    })
    private String species;

    @JsonView({
            SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ProductBasicView.class, SystemViews.AdvertisingDetailView.class,
            SystemViews.RequestDetailView.class
    })
    private String seasonType;

    @JsonView({
            SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ProductBasicView.class, SystemViews.AdvertisingDetailView.class,
            SystemViews.RequestDetailView.class
    })
    private String harvestMonths;

}

