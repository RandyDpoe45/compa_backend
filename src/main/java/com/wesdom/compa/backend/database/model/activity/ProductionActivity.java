package com.wesdom.compa.backend.database.model.activity;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.database.model.estatesegment.ProductInStateSegment;
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
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ProductionActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.ProductionActivityBasicView.class})
    private Long id;

    @JsonView({SystemViews.ProductionActivityBasicView.class})
    private Boolean approved = false;

    @JsonView({SystemViews.ProductionActivityBasicView.class})
    @Column(length = 600)
    private String comments;

    @JsonView({SystemViews.ProductionActivityBasicView.class})
    @Column(length = 600)
    private String promoterComments;

    @ManyToOne(targetEntity = ProductInStateSegment.class, fetch = FetchType.LAZY)
    @JsonView({SystemViews.ProductionActivityBasicView.class})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotFound(action = NotFoundAction.IGNORE)
    private ProductInStateSegment productInStateSegment;

    @ManyToOne(targetEntity = Activity.class,fetch = FetchType.LAZY)
    @JsonView({SystemViews.ProductionActivityBasicView.class})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotFound(action = NotFoundAction.IGNORE)
    private Activity activity;

    @OneToMany(targetEntity = ProductionActivityAnswer.class,fetch = FetchType.LAZY,mappedBy = "productionActivity", cascade = CascadeType.REMOVE)
//    @JsonView({SystemViews.ProductionActivityBasicView.class})
    @NotFound(action = NotFoundAction.IGNORE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ProductionActivityAnswer> activityAnswers;

    @JsonView({SystemViews.ProductionActivityBasicView.class})
    public Float getActivityScore(){
        if (activityAnswers.isEmpty()){
            return 0f;
        }
        Float activityScore = this.activityAnswers.stream()
                .map(x -> (x != null && x.getActivityOption() != null) ?
                        x.getActivityOption().getScore() : 0)
                .reduce(0f,Float::sum);
        return activityScore / this.activityAnswers.size();
    }

}
