package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Flora {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.NearbyFloraBasicView.class})
    private Long id;

    @JsonView({SystemViews.NearbyFloraBasicView.class})
    private String type;

    @JsonView({SystemViews.NearbyFloraBasicView.class})
    private String species;

    @JsonView({SystemViews.NearbyFloraBasicView.class})
    private String variety;
}
