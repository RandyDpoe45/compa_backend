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

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class RequestOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.RequestOfferBasicView.class})
    private Long id;

    @JsonView({SystemViews.RequestOfferBasicView.class})
    private String status;

    @ManyToOne(targetEntity = Request.class,fetch = FetchType.LAZY)
    private Request request;

    @JsonView({SystemViews.RequestOfferBasicView.class})
    private BigDecimal price;

    @JsonView({SystemViews.RequestOfferBasicView.class})
    @Column(length = 800)
    private String comments;

    @JsonView({SystemViews.RequestOfferBasicView.class})
    private BigDecimal amount;

    @JsonView({SystemViews.RequestOfferBasicView.class})
    @ManyToOne(targetEntity = MeasureUnit.class, fetch = FetchType.LAZY)
    private MeasureUnit measureUnit;

    @JsonView({SystemViews.RequestOfferBasicView.class})
    @ManyToOne(targetEntity = ManufacturerGroup.class, fetch = FetchType.LAZY)
    private ManufacturerGroup manufacturerGroup;

    @JsonView({SystemViews.RequestOfferBasicView.class})
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate deliveryDate;

}
