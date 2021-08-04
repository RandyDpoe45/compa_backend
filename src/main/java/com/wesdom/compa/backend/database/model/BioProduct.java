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

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class BioProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({
            SystemViews.BioProductBasicView.class, SystemViews.BioProductDetailView.class,
            SystemViews.PromoterDetailedView.class
    })
    private Long id;

    @JsonView({
            SystemViews.BioProductBasicView.class, SystemViews.BioProductDetailView.class,
            SystemViews.PromoterDetailedView.class
    })
    private String name;

    @JsonView({
            SystemViews.BioProductBasicView.class, SystemViews.PromoterDetailedView.class
    })
    private String type;

    @JsonView({
            SystemViews.BioProductBasicView.class, SystemViews.PromoterDetailedView.class
    })
    @Column(length = 1000)
    private String description;

    @JsonView({SystemViews.BioProductBasicView.class})
    @ManyToOne(targetEntity = Promoter.class, fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private Promoter promoter;
}
