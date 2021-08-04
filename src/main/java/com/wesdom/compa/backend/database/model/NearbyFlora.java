package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
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
public class NearbyFlora {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.NearbyFloraBasicView.class})
    private Long id;

    @JsonView({SystemViews.NearbyFloraBasicView.class})
    @ManyToOne(targetEntity = Flora.class, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotFound(action = NotFoundAction.IGNORE)
    private Flora flora;

    @ManyToOne(targetEntity = EstateSegment.class, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonView({SystemViews.NearbyFloraBasicView.class})
    @NotFound(action = NotFoundAction.IGNORE)
    private EstateSegment estateSegment;

    @JsonView({SystemViews.NearbyFloraBasicView.class})
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonView({SystemViews.NearbyFloraBasicView.class})
    private String abundanceType;
}
