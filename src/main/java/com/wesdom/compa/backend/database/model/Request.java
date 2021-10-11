package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.wesdom.compa.backend.database.model.users.Association;
import com.wesdom.compa.backend.database.model.users.CommercialPartner;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({
            SystemViews.RequestBasicView.class, SystemViews.RequestOfferDetailView.class,
            SystemViews.RequestDetailView.class
    })
    private Long id;

    @JsonView({
            SystemViews.RequestBasicView.class, SystemViews.RequestOfferDetailView.class,
            SystemViews.RequestDetailView.class
    })
    private String type;

    @JsonView({
            SystemViews.RequestBasicView.class, SystemViews.RequestOfferDetailView.class,
            SystemViews.RequestDetailView.class
    })
    private String status;

    @ManyToOne(targetEntity = CommercialPartner.class, fetch = FetchType.LAZY)
    @JsonView({
            SystemViews.RequestBasicView.class, SystemViews.RequestOfferDetailView.class,
            SystemViews.RequestDetailView.class
    })
    @NotFound(action = NotFoundAction.IGNORE)
    private CommercialPartner commercialPartner;

    @ManyToOne(targetEntity = MeasureUnit.class, fetch = FetchType.LAZY)
    @JsonView({
            SystemViews.RequestBasicView.class,SystemViews.RequestDetailView.class
    })
    @NotFound(action = NotFoundAction.IGNORE)
    private MeasureUnit measureUnit;

    @ManyToOne(targetEntity = Association.class, fetch = FetchType.LAZY)
    @JsonView({
            SystemViews.RequestBasicView.class, SystemViews.RequestOfferDetailView.class,
            SystemViews.RequestDetailView.class
    })
    @NotFound(action = NotFoundAction.IGNORE)
    private Association association;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JsonView({
            SystemViews.RequestBasicView.class, SystemViews.RequestOfferDetailView.class,
            SystemViews.RequestDetailView.class
    })
    @NotFound(action = NotFoundAction.IGNORE)
    private Product product;

    @JsonView({
            SystemViews.RequestBasicView.class, SystemViews.RequestOfferDetailView.class,
            SystemViews.RequestDetailView.class
    })
    private BigDecimal pricePerUnit;

    @JsonView({
            SystemViews.RequestBasicView.class, SystemViews.RequestOfferDetailView.class,
            SystemViews.RequestDetailView.class
    })
    private BigDecimal deductedPricePerUnit;

    @JsonView({
            SystemViews.RequestBasicView.class, SystemViews.RequestOfferDetailView.class,
            SystemViews.RequestDetailView.class
    })
    private BigDecimal amount;

    @Column(length = 1000)
    @JsonView({SystemViews.RequestDetailView.class})
    private String statusHistory;

    @Column(length = 1000)
    @JsonView({SystemViews.RequestDetailView.class})
    private String statusDateHistory;

    @Column(length = 1000)
    @JsonView({SystemViews.RequestDetailView.class})
    private String productionConditions;

    @Column(length = 1000)
    @JsonView({SystemViews.RequestDetailView.class})
    private String transportConditions;

    @Column(length = 1000)
    @JsonView({SystemViews.RequestDetailView.class})
    private String packingConditions;

    @JsonView({SystemViews.RequestBasicView.class, SystemViews.RequestDetailView.class})
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate requestDate;

    @JsonView({SystemViews.RequestBasicView.class, SystemViews.RequestDetailView.class})
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate deliveryDate;

    @JsonView({SystemViews.RequestBasicView.class, SystemViews.RequestDetailView.class})
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate maxDeliveryDate;

    @JsonView({SystemViews.RequestDetailView.class})
    private String deliveryHour;

    @JsonView({SystemViews.RequestDetailView.class})
    private String deliveryPlace;

    @JsonView({SystemViews.RequestDetailView.class})
    @Column(length = 600)
    private String comments;

    @JsonView({SystemViews.RequestDetailView.class})
    private BigDecimal maxAmountOffer;

}
