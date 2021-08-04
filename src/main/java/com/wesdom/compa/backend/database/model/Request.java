package com.wesdom.compa.backend.database.model;

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
    @JsonView({SystemViews.RequestBasicView.class, SystemViews.RequestOfferDetailView.class})
    private Long id;

    @JsonView({SystemViews.RequestBasicView.class, SystemViews.RequestOfferDetailView.class})
    private String type;

    @ManyToOne(targetEntity = CommercialPartner.class, fetch = FetchType.LAZY)
    @JsonView({SystemViews.RequestBasicView.class, SystemViews.RequestOfferDetailView.class})
    @NotFound(action = NotFoundAction.IGNORE)
    private CommercialPartner commercialPartner;

    @ManyToOne(targetEntity = Association.class, fetch = FetchType.LAZY)
    @JsonView({SystemViews.RequestBasicView.class, SystemViews.RequestOfferDetailView.class})
    @NotFound(action = NotFoundAction.IGNORE)
    private Association association;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JsonView({SystemViews.RequestBasicView.class, SystemViews.RequestOfferDetailView.class})
    @NotFound(action = NotFoundAction.IGNORE)
    private Product product;

    @JsonView({SystemViews.RequestBasicView.class, SystemViews.RequestOfferDetailView.class})
    private BigDecimal pricePerUnit;

    @JsonView({SystemViews.RequestBasicView.class, SystemViews.RequestOfferDetailView.class})
    private BigDecimal deductedPricePerUnit;

    @JsonView({SystemViews.RequestBasicView.class, SystemViews.RequestOfferDetailView.class})
    private BigDecimal amount;

    @ManyToOne(targetEntity = MeasureUnit.class, fetch = FetchType.LAZY)
    @JsonView({SystemViews.RequestBasicView.class, SystemViews.RequestOfferDetailView.class})
    @NotFound(action = NotFoundAction.IGNORE)
    private MeasureUnit measureUnit;

    @Column(length = 1000)
    @JsonView({SystemViews.RequestBasicView.class})
    private  String productionConditions;

    @Column(length = 1000)
    @JsonView({SystemViews.RequestBasicView.class})
    private String transportConditions;

    @Column(length = 1000)
    @JsonView({SystemViews.RequestBasicView.class})
    private String packingConditions;

    @JsonView({SystemViews.RequestBasicView.class})
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate deliveryDate;

    @JsonView({SystemViews.RequestBasicView.class})
    private String deliveryHour;

    @JsonView({SystemViews.RequestBasicView.class, SystemViews.RequestOfferDetailView.class})
    private String deliveryPlace;

    @JsonView({SystemViews.RequestBasicView.class})
    @Column(length = 600)
    private String comments;

}
