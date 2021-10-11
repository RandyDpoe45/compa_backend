package com.wesdom.compa.backend.database.model.estatesegment;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.database.model.Department;
import com.wesdom.compa.backend.database.model.ManufacturerGroup;
import com.wesdom.compa.backend.database.model.Municipality;
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
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Estate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({
            SystemViews.EstateBasicView.class, SystemViews.EstateSegmentBasicView.class,
            SystemViews.EstateSegmentDetailView.class
    })
    private Long id;

    @JsonView({
            SystemViews.EstateBasicView.class, SystemViews.EstateSegmentBasicView.class,
            SystemViews.EstateSegmentDetailView.class
    })
    private String name;

    @ManyToOne(targetEntity = Department.class, fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonView({SystemViews.EstateBasicView.class,SystemViews.EstateSegmentDetailView.class})
    private Department department;

    @ManyToOne(targetEntity = Municipality.class, fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonView({SystemViews.EstateBasicView.class,SystemViews.EstateSegmentDetailView.class})
    private Municipality municipality;

    @JsonView({SystemViews.EstateBasicView.class,SystemViews.EstateSegmentDetailView.class})
    private String plantation;

    @JsonView({SystemViews.EstateBasicView.class,SystemViews.EstateSegmentDetailView.class})
    private BigDecimal latitude;

    @JsonView({SystemViews.EstateBasicView.class,SystemViews.EstateSegmentDetailView.class})
    private BigDecimal longitude;

    @JsonView({SystemViews.EstateBasicView.class,SystemViews.EstateSegmentDetailView.class})
    private BigDecimal totalArea;

    @JsonView({SystemViews.EstateBasicView.class,SystemViews.EstateSegmentDetailView.class})
    private BigDecimal forestArea;

    @JsonView({SystemViews.EstateBasicView.class,SystemViews.EstateSegmentDetailView.class})
    private Boolean hasSpacePlan;

    @JsonView({SystemViews.EstateBasicView.class,SystemViews.EstateSegmentDetailView.class})
    private Boolean hasFuturePlan;

    @JsonView({SystemViews.EstateBasicView.class,SystemViews.EstateSegmentDetailView.class})
    @ManyToOne(targetEntity = ManufacturerGroup.class, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotFound(action = NotFoundAction.IGNORE)
    private ManufacturerGroup manufacturerGroup;

    @OneToMany(targetEntity = EstateSegment.class, fetch = FetchType.LAZY, mappedBy = "estate",orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<EstateSegment> estateSegmentList;
}
