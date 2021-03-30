package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ExpertAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.ExpertAlertBasicView.class})
    private Long id;

    @JsonView({SystemViews.ExpertAlertBasicView.class})
    @ManyToOne(targetEntity = Promoter.class,fetch = FetchType.LAZY)
    private Promoter promoter;

    @JsonView({SystemViews.ExpertAlertBasicView.class})
    @ManyToOne(targetEntity = ProductInStateSegment.class, fetch = FetchType.LAZY)
    private ProductInStateSegment productInStateSegment;

    @JsonView({SystemViews.ExpertAlertBasicView.class})
    @Column(length = 600)
    private String issue;
}
