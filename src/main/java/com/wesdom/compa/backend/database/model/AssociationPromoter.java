package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.database.model.users.Association;
import com.wesdom.compa.backend.database.model.users.Promoter;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
    @NotFound(action = NotFoundAction.IGNORE)
    private Association association;

    @ManyToOne(targetEntity = Promoter.class, fetch = FetchType.LAZY)
    @JsonView({SystemViews.AssociationMemberBasicView.class})
    @NotFound(action = NotFoundAction.IGNORE)
    private Promoter promoter;

}
