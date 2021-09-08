package com.wesdom.compa.backend.database.model.estatesegment;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("C")
@NoArgsConstructor
@Accessors(chain = true)
@JsonTypeName("ConservationSegment")
public class ConservationSegment extends EstateSegment{

    @JsonView({SystemViews.EstateSegmentDetailView.class})
    private String conservationStatus;
}
