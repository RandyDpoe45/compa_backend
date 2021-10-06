package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.NotificationJpaRepository;
import com.wesdom.compa.backend.database.model.users.Notification;
import com.wesdom.compa.backend.database.repositories.INotificationRepository;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Service
public class NotificationRepositoryImpl implements INotificationRepository {
    
    @Autowired
    private NotificationJpaRepository notificationJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Notification get(Long id) {
        return notificationJpaRepository.getOne(id);
    }

    @Override
    public Page<Notification> getAll(Map<String, String> params) {
        IPredicateBuilder<Notification> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return notificationJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Notification save(Notification notification) {
        notification = notificationJpaRepository.saveAndFlush(notification);
        em.refresh(notification);
        return notification;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Notification update(Long notificationId, Notification notification) {
        notification = notificationJpaRepository.saveAndFlush(notification);
        em.refresh(notification);
        return notification;
    }

    @Override
    public void delete(List<Long> notificationId) {
        List<Notification> notifications = notificationJpaRepository.findAllById(notificationId);
        notificationJpaRepository.deleteAll(notifications);
    }
}
