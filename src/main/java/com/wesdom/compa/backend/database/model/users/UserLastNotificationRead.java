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
public class UserLastNotificationRead {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemViews.UserLastNotificationReadBasicView.class})
    private Long id;

    @JsonView({SystemViews.UserLastNotificationReadBasicView.class})
    private Long notificationId;

    @OneToOne(targetEntity = AuthUser.class, fetch = FetchType.LAZY)
    private AuthUser authUser;
}
