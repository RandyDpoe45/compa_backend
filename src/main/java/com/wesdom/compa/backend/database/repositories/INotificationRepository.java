package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.users.Notification;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface INotificationRepository {

    public Notification get(Long id);
    Page<Notification> getAll(Map<String, String> params);
    public Notification save(Notification notification);
    public Notification update(Long notificationId, Notification notification);
    public void delete(List<Long> notificationId);
}
