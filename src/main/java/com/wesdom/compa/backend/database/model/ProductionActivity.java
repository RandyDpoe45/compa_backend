package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ProductionActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.ProductionActivityBasicView.class,SystemViews.ExpertVisitNoteBasicView.class})
    private Long id;

    @JsonView({SystemViews.ProductionActivityBasicView.class,SystemViews.ExpertVisitNoteBasicView.class})
    private Boolean approved = false;

    @ManyToOne(targetEntity = ProductInStateSegment.class, fetch = FetchType.LAZY)
    @JsonView({SystemViews.ProductionActivityBasicView.class})
    private ProductInStateSegment productInStateSegment;

    @ManyToOne(targetEntity = Activity.class,fetch = FetchType.LAZY)
    @JsonView({SystemViews.ProductionActivityBasicView.class,SystemViews.ExpertVisitNoteBasicView.class})
    private Activity activity;

    @OneToMany(targetEntity = ProductionActivityAnswer.class,fetch = FetchType.LAZY,mappedBy = "productionActivity")
//    @JsonView({SystemViews.ProductionActivityBasicView.class})
    private List<ProductionActivityAnswer> activityAnswers;

}
