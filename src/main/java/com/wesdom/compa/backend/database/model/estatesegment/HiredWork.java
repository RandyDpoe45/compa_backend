package com.wesdom.compa.backend.database.model.estatesegment;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@DiscriminatorValue("H")
@Accessors(chain = true)
@JsonTypeName("HiredWork")
public class HiredWork extends OwnWork{

    @Column(length = 600)
    @JsonView({SystemViews.ProductWorkBasicView.class})
    private String names;

    @JsonView({SystemViews.ProductWorkBasicView.class})
    private BigDecimal value;

    public HiredWork() {
        this.setWorkType("Contrato");
    }
}
