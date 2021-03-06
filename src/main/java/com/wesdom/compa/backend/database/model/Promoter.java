package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Promoter extends BaseUser{

    @JsonView({SystemViews.PromoterBasicView.class})
    private String plantation;

    @JsonView({SystemViews.PromoterBasicView.class})
    private BigDecimal latitude;

    @JsonView({SystemViews.PromoterBasicView.class})
    private BigDecimal longitude;

    @JsonView({SystemViews.PromoterBasicView.class})
    @OneToMany(targetEntity = ManufacturerType.class, fetch = FetchType.LAZY)
    private List<ManufacturerType> type;
}
