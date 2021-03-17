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
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String stage;

    @ManyToMany(targetEntity = ActivityOption.class, fetch = FetchType.LAZY)
    private List<ActivityOption> activityOption;

    @ManyToMany(targetEntity = ActivityOption.class, fetch = FetchType.LAZY)
    private List<ActivityOption> workOptions;
}
