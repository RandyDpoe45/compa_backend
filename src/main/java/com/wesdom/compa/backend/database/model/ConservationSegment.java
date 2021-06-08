package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@DiscriminatorValue("C")
@NoArgsConstructor
@Accessors(chain = true)
@JsonTypeName("ConservationSegment")
public class ConservationSegment extends EstateSegment{

}
