package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ManufacturerGroup {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    @JsonView({SystemViews.GroupBasicView.class,SystemViews.GroupDetailedView.class, SystemViews.EstateBasicView.class
            ,SystemViews.EstateSegmentDetailView.class})
    private Long id;

    @JsonView({SystemViews.GroupBasicView.class,SystemViews.GroupDetailedView.class, SystemViews.EstateBasicView.class
            ,SystemViews.EstateSegmentDetailView.class})
    private String name;

    @JsonView({SystemViews.GroupBasicView.class,SystemViews.GroupDetailedView.class})
    @ManyToOne(targetEntity = GroupType.class, fetch = FetchType.LAZY)
    private GroupType groupType;

//    @JsonView({SystemViews.GroupDetailedView.class})
    @OneToMany(targetEntity = Estate.class, mappedBy = "manufacturerGroup",fetch = FetchType.LAZY)
    private List<Estate> estateList;

    @JsonView({SystemViews.GroupDetailedView.class})
    @ManyToMany(targetEntity = Manufacturer.class, fetch = FetchType.LAZY)
    private List<Manufacturer> manufacturerList;
}
