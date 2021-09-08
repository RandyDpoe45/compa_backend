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
public class PostHarvestCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.PostHarvestCheckBasicView.class})
    private Long id;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    private Product product;

    @JsonView({SystemViews.PostHarvestCheckBasicView.class})
    private String description;
}
