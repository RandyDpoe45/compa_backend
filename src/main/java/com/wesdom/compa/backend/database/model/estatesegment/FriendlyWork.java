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

@Entity
@Getter
@Setter
@DiscriminatorValue("F")
@Accessors(chain = true)
@JsonTypeName("FriendlyWork")
public class FriendlyWork extends ProductWork{

    @Column(length = 600)
    @JsonView({SystemViews.ProductWorkBasicView.class})
    private String names;

    public FriendlyWork() {
        this.setWorkType("Manocambiada");
    }
}
