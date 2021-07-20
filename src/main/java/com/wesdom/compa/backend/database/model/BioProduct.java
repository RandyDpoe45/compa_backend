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
public class BioProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.BioProductBasicView.class})
    private Long id;

    @JsonView({SystemViews.BioProductBasicView.class})
    private String name;

    @JsonView({SystemViews.BioProductBasicView.class})
    private String description;

    @ManyToOne(targetEntity = Promoter.class, fetch = FetchType.LAZY)
    private Promoter promoter;
}
