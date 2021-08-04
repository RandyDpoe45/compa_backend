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

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class OptionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.OptionAnswerBasicView.class})
    private Long id;

    @ManyToOne(targetEntity = ActivityOption.class, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotFound(action = NotFoundAction.IGNORE)
    private ActivityOption activityOption;

    @JsonView({SystemViews.OptionAnswerBasicView.class})
    private String value;

}
