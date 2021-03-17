package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
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
public class ProductInStateSegment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.ProductInStateBasicView.class})
    private Long id;

    @ManyToOne(targetEntity = Product.class,fetch = FetchType.LAZY)
    @JsonView({SystemViews.ProductInStateBasicView.class})
    private Product product;

    @ManyToOne(targetEntity = EstateSegment.class, fetch = FetchType.LAZY)
    @JsonView({SystemViews.ProductInStateBasicView.class})
    private EstateSegment estateSegment;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonView({SystemViews.ProductInStateBasicView.class})
    private LocalDate beginning;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonView({SystemViews.ProductInStateBasicView.class})
    private LocalDate end;

    @JsonView({SystemViews.ProductInStateBasicView.class})
    private String currentStage;

    @JsonView({SystemViews.ProductInStateBasicView.class})
    private BigDecimal area;

}
