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
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class RequestOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.RequestOfferBasicView.class, SystemViews.RequestOfferDetailView.class})
    private Long id;

    @JsonView({SystemViews.RequestOfferBasicView.class, SystemViews.RequestOfferDetailView.class})
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(targetEntity = RequestOfferStatus.class)
    private RequestOfferStatus requestOfferStatus;

    @ManyToOne(targetEntity = Request.class,fetch = FetchType.LAZY)
    @JsonView({SystemViews.RequestOfferDetailView.class})
    @NotFound(action = NotFoundAction.IGNORE)
    private Request request;

    @JsonView({SystemViews.RequestOfferBasicView.class, SystemViews.RequestOfferDetailView.class})
    private BigDecimal price;

    @JsonView({SystemViews.RequestOfferBasicView.class, SystemViews.RequestOfferDetailView.class})
    @Column(length = 800)
    private String comments;

    @JsonView({SystemViews.RequestOfferBasicView.class, SystemViews.RequestOfferDetailView.class})
    private BigDecimal amount;

    @JsonView({SystemViews.RequestOfferBasicView.class, SystemViews.RequestOfferDetailView.class})
    @ManyToOne(targetEntity = MeasureUnit.class, fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private MeasureUnit measureUnit;

    @JsonView({SystemViews.RequestOfferBasicView.class, SystemViews.RequestOfferDetailView.class})
    @ManyToOne(targetEntity = ManufacturerGroup.class, fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private ManufacturerGroup manufacturerGroup;

    @JsonView({SystemViews.RequestOfferBasicView.class, SystemViews.RequestOfferDetailView.class})
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate deliveryDate;

    @JsonView({SystemViews.RequestOfferBasicView.class, SystemViews.RequestOfferDetailView.class})
    private String deliveryCode;

    @JsonView({SystemViews.RequestOfferDetailView.class})
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToMany(targetEntity = ProductInStateSegment.class, fetch = FetchType.LAZY)
    private List<ProductInStateSegment> productInStateSegmentList;

}
