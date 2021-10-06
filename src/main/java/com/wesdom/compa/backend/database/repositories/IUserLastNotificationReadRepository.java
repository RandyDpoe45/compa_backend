package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.users.UserLastNotificationRead;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IUserLastNotificationReadRepository {
    public UserLastNotificationRead get(Long id);
    UserLastNotificationRead getByAuthUserId(Long id);
    public UserLastNotificationRead save(UserLastNotificationRead flora);
    public UserLastNotificationRead update(Long floraId, UserLastNotificationRead flora);
    public void delete(Long floraId);
}
