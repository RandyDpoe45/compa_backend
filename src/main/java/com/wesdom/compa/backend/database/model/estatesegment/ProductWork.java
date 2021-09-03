package com.wesdom.compa.backend.database.model.estatesegment;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.database.model.ProductionStage;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
@NoArgsConstructor
@Accessors(chain = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "WorkType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OwnWork.class, name = "OwnWork"),
        @JsonSubTypes.Type(value = FriendlyWork.class, name = "FriendlyWork"),
        @JsonSubTypes.Type(value = HiredWork.class, name = "HiredWork")
})
public class ProductWork {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.ProductWorkBasicView.class})
    private Long id;

    @JsonView({SystemViews.ProductWorkBasicView.class})
    private String workType;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(targetEntity = ProductionStage.class)
    @JsonView({SystemViews.ProductWorkBasicView.class})
    private ProductionStage productionStage;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(targetEntity = ProductInStateSegment.class, fetch = FetchType.LAZY)
//    @JsonView({SystemViews.ProductWorkBasicView.class})
    private ProductInStateSegment productInStateSegment;

    @JsonView({SystemViews.ProductWorkBasicView.class})
    private Float hours;

}
