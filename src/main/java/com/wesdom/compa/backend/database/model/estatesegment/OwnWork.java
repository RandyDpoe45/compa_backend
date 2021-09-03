package com.wesdom.compa.backend.database.model.estatesegment;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.database.model.users.Manufacturer;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@DiscriminatorValue("O")
@Accessors(chain = true)
@JsonTypeName("OwnWork")
public class OwnWork extends ProductWork{

    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(targetEntity = Manufacturer.class)
    @JsonView({SystemViews.ProductWorkBasicView.class})
    private Manufacturer manufacturer;

    public OwnWork() {
        this.setWorkType("Propio");
    }
}
