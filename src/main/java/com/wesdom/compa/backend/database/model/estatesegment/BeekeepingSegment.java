package com.wesdom.compa.backend.database.model.estatesegment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("B")
@NoArgsConstructor
@Accessors(chain = true)
@JsonTypeName("BeekeepingSegment")
public class BeekeepingSegment extends EstateSegment{

    @JsonView({SystemViews.EstateSegmentDetailView.class})
    private Long numberOfChambers;

    @JsonView({SystemViews.EstateSegmentDetailView.class})
    private Long nSingleHiveChamber;

    @JsonView({SystemViews.EstateSegmentDetailView.class})
    private Long nDoubleHiveChamber;

    @JsonView({SystemViews.EstateSegmentDetailView.class})
    private Long nTripleHiveChamber;

    @JsonView({SystemViews.EstateSegmentDetailView.class})
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate creationDate;

    @JsonView({SystemViews.EstateSegmentDetailView.class})
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate lastDivision;

    @JsonView({SystemViews.EstateSegmentDetailView.class})
    private String boxOrigin;

    @JsonView({SystemViews.EstateSegmentDetailView.class})
    private String troughType;

    @JsonView({SystemViews.EstateSegmentDetailView.class})
    private BigDecimal promHarvest;

    @JsonView({SystemViews.EstateSegmentDetailView.class})
    private BigDecimal maxHarvest;

    @JsonView({SystemViews.EstateSegmentDetailView.class})
    @ManyToMany(targetEntity = ApiaryWoodType.class, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<ApiaryWoodType> woodTypeList;

}
