package com.wesdom.compa.backend.database.model.users;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.NotificationBasicView.class})
    private Long id;

    @Column(length = 600)
    @JsonView({SystemViews.NotificationBasicView.class})
    private String message;

    //PUSH, SMS, EMAIL
    @JsonView({SystemViews.NotificationBasicView.class})
    private String type;

    @JsonView({SystemViews.NotificationBasicView.class})
    private String entity;

    @JsonView({SystemViews.NotificationBasicView.class})
    private String entityTarget;

    @Column(length = 1000)
    @JsonView({SystemViews.NotificationBasicView.class})
    private String ids;


}
