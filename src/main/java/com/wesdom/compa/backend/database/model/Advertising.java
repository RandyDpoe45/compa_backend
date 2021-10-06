package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.wesdom.compa.backend.database.model.estatesegment.ProductInStateSegment;
import com.wesdom.compa.backend.database.model.users.Association;
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
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Advertising {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.AdvertisingBasicView.class, SystemViews.AdvertisingDetailView.class})
    private Long id;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JsonView({SystemViews.AdvertisingBasicView.class, SystemViews.AdvertisingDetailView.class})
    @NotFound(action = NotFoundAction.IGNORE)
    private Product product;

    @ManyToOne(targetEntity = Association.class, fetch = FetchType.LAZY)
    @JsonView({SystemViews.AdvertisingBasicView.class, SystemViews.AdvertisingDetailView.class})
    @NotFound(action = NotFoundAction.IGNORE)
    private Association association;

    @JsonView({SystemViews.AdvertisingDetailView.class})
    @Column(length = 600)
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonView({SystemViews.AdvertisingBasicView.class, SystemViews.AdvertisingDetailView.class})
    private LocalDate date;

    @ManyToMany(targetEntity = ManufacturerGroup.class, fetch = FetchType.LAZY)
    @JsonView({SystemViews.AdvertisingDetailView.class})
    @NotFound(action = NotFoundAction.IGNORE)
    private List<ManufacturerGroup> manufacturerGroupList;


    @JsonView({SystemViews.AdvertisingDetailView.class})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany(targetEntity = ProductInStateSegment.class, fetch = FetchType.LAZY)
    private List<ProductInStateSegment> productInStateSegmentList;
    //crear lista de productInjstateSegment
}
