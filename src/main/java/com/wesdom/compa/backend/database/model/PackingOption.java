package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class PackingOption {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.PackingOptionBasicView.class})
    private Long id;

    @ManyToOne(targetEntity = ProductType.class, fetch = FetchType.LAZY)
    @JsonView({SystemViews.PackingOptionBasicView.class})
    private ProductType productType;

    @JsonView({SystemViews.PackingOptionBasicView.class})
    private String description;
}
