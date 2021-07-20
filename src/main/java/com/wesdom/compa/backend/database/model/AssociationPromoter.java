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
public class AssociationPromoter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.AssociationMemberBasicView.class})
    private Long id;

    @ManyToOne(targetEntity = Association.class,fetch = FetchType.LAZY)
    private Association association;

    @ManyToOne(targetEntity = Promoter.class, fetch = FetchType.LAZY)
    @JsonView({SystemViews.AssociationMemberBasicView.class})
    private Promoter promoter;

}
