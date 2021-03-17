package com.wesdom.compa.backend.database.model;

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
    private Long id;

    private String name;

    //either Open or closed answer
    private String optionType;

    @ManyToMany(targetEntity = OptionAnswer.class,fetch = FetchType.LAZY)
    private List<OptionAnswer> optionAnswersList;
}
