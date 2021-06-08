package com.wesdom.compa.backend.database.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Solicitude extends BaseUser{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String type;

    @ManyToOne(targetEntity = CommercialPartner.class, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CommercialPartner creator;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    private BigDecimal price;

    private Double amount;

    @Column(length = 1000)
    private String productionConditions;

    @Column(length = 1000)
    private String packingConditions;

    @Column(length = 1000)
    private String transportConditions;

    @Column(length = 1000)
    private LocalDate deliveryDate;

    @Column(length = 1000)
    private String deliveryPlace;

    @Column(length = 1000)
    private String comments;

    @Column(length = 1000)
    private Double recievedAmount;

    @ManyToOne(targetEntity = MeasureUnit.class, fetch = FetchType.LAZY)
    private MeasureUnit measureUnit;
}
