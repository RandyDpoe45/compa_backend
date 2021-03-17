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
public class ActivityOption {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.ProductionActivityAnswerBasicView.class})
    private Long id;

    @JsonView({SystemViews.ProductionActivityAnswerBasicView.class})
    private String name;

    //daily or regular
    @JsonView({SystemViews.ProductionActivityAnswerBasicView.class})
    private String optionType;

    //either Open or closed answer
    @JsonView({SystemViews.ProductionActivityAnswerBasicView.class})
    private String answerType;

    @ManyToMany(targetEntity = OptionAnswer.class,fetch = FetchType.LAZY)
    private List<OptionAnswer> optionAnswersList;
}
