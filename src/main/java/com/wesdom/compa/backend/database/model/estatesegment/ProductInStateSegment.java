package com.wesdom.compa.backend.database.model.estatesegment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.wesdom.compa.backend.database.model.Product;
import com.wesdom.compa.backend.database.model.activity.ProductionActivity;
import com.wesdom.compa.backend.database.model.ProductionStage;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ProductInStateSegment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({
            SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ExpertVisitBasicView.class, SystemViews.AdvertisingDetailView.class
    })
    private Long id;

    @ManyToOne(targetEntity = Product.class,fetch = FetchType.LAZY)
    @JsonView({
            SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ExpertVisitBasicView.class
    })
    @NotFound(action = NotFoundAction.IGNORE)
    private Product product;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(targetEntity = EstateSegment.class, fetch = FetchType.LAZY)
    @JsonView({SystemViews.ProductInStateBasicView.class, SystemViews.ExpertVisitBasicView.class,})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EstateSegment estateSegment;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonView({
            SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ExpertVisitBasicView.class
    })
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate beginning;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonView({
            SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ExpertVisitBasicView.class
    })
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate end;

    @JsonView({
            SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class
    })
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(targetEntity = ProductionStage.class, fetch = FetchType.LAZY)
    private ProductionStage currentStage;

    @JsonView({
            SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ExpertVisitBasicView.class, SystemViews.AdvertisingDetailView.class
    })
    private BigDecimal area;

    @JsonView({
            SystemViews.ProductInStateBasicView.class,SystemViews.ProductionActivityBasicView.class,
            SystemViews.ExpertVisitBasicView.class
    })
    private BigDecimal totalHarvest;

    @OneToMany(targetEntity = ProductionActivity.class, fetch = FetchType.LAZY, mappedBy = "productInStateSegment")
    private List<ProductionActivity> productionActivityList;

    @JsonView({
            SystemViews.ProductInStateBasicView.class,SystemViews.ExpertVisitBasicView.class,
            SystemViews.AdvertisingDetailView.class
    })
    public Float getProductionScore(){
        if (this.productionActivityList.isEmpty())
            return 0f;
        Float productionScore = this.productionActivityList.stream()
                .map(x -> x.getActivityScore())
                .reduce(0f, Float::sum);
        return productionScore / this.productionActivityList.size();
    }
}
