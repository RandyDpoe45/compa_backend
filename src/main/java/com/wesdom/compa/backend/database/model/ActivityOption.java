package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonView;
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
public class ActivityOption {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({
            SystemViews.ProductionActivityAnswerBasicView.class, SystemViews.ActivityOptionBasicView.class,
            SystemViews.ActivityBasicView.class
    })
    private Long id;

    @JsonView({
            SystemViews.ProductionActivityAnswerBasicView.class, SystemViews.ActivityOptionBasicView.class,
            SystemViews.ActivityBasicView.class
    })
    private String name;

    @JsonView({
            SystemViews.ProductionActivityAnswerBasicView.class, SystemViews.ActivityOptionBasicView.class,
            SystemViews.ActivityBasicView.class
    })
    private Float score;

    //daily or regular
    @JsonView({
            SystemViews.ProductionActivityAnswerBasicView.class, SystemViews.ActivityOptionBasicView.class,
            SystemViews.ActivityBasicView.class
    })
    private String optionType;

    //either Open or closed answer
    @JsonView({
            SystemViews.ProductionActivityAnswerBasicView.class, SystemViews.ActivityOptionBasicView.class,
            SystemViews.ActivityBasicView.class
    })
    private String answerType;

    @ManyToOne(targetEntity = Activity.class, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotFound(action = NotFoundAction.IGNORE)
    private Activity activity;

//    @ManyToMany(targetEntity = OptionAnswer.class,fetch = FetchType.LAZY)
//    private List<OptionAnswer> optionAnswersList;
}
