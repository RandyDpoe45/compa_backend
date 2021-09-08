package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.wesdom.compa.backend.database.model.estatesegment.ProductInStateSegment;
import com.wesdom.compa.backend.database.model.users.Promoter;
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
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ExpertVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.ExpertVisitBasicView.class,SystemViews.ExpertVisitNoteBasicView.class})
    private Long id;

    @JsonView({SystemViews.ExpertVisitBasicView.class,SystemViews.ExpertVisitNoteBasicView.class})
    private String status;

    @JsonView({SystemViews.ExpertVisitBasicView.class,SystemViews.ExpertVisitNoteBasicView.class})
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    @JsonView({SystemViews.ExpertVisitBasicView.class})
    @ManyToOne(targetEntity = ProductInStateSegment.class, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotFound(action = NotFoundAction.IGNORE)
    private ProductInStateSegment productInStateSegment;

    @JsonView({SystemViews.ExpertVisitBasicView.class})
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(targetEntity = Promoter.class, fetch = FetchType.LAZY)
    private Promoter promoter;

    @JsonView({SystemViews.ExpertVisitBasicView.class,SystemViews.ExpertVisitNoteBasicView.class})
    @Column(length = 800)
    private String comment;

    @JsonView({SystemViews.ExpertVisitBasicView.class,SystemViews.ExpertVisitNoteBasicView.class})
    @Column(length = 800)
    private String futureTask;

    @JsonView({SystemViews.ExpertVisitBasicView.class,SystemViews.ExpertVisitNoteBasicView.class})
    @Column(length = 500)
    private String floraQuality;

    @JsonView({SystemViews.ExpertVisitBasicView.class,SystemViews.ExpertVisitNoteBasicView.class})
    private Boolean hasWater;

    @JsonView({SystemViews.ExpertVisitBasicView.class,SystemViews.ExpertVisitNoteBasicView.class})
    @Column(length = 800)
    private String isClean;

    @JsonView({SystemViews.ExpertVisitBasicView.class})
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(targetEntity = ExpertVisit.class, fetch = FetchType.LAZY)
    private ExpertVisitType expertVisitType;

    @JsonView({SystemViews.ExpertVisitBasicView.class})
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(targetEntity = ProductionStage.class, fetch = FetchType.LAZY)
    private ProductionStage productionStage;

}
