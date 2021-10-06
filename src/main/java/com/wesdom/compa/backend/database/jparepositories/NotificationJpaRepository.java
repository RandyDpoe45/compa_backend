package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.users.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationJpaRepository extends JpaRepository<Notification,Long>, JpaSpecificationExecutor<Notification> {
}
