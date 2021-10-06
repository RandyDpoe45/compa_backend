package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.FloraJpaRepository;
import com.wesdom.compa.backend.database.jparepositories.UserLastNotificationReadJpaRepository;
import com.wesdom.compa.backend.database.model.Flora;
import com.wesdom.compa.backend.database.model.users.UserLastNotificationRead;
import com.wesdom.compa.backend.database.repositories.IFloraRepository;
import com.wesdom.compa.backend.database.repositories.IUserLastNotificationReadRepository;
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
import java.util.Map;

@Service
public class UserLastNotificationReadRepositoryImpl implements IUserLastNotificationReadRepository {
    
    @Autowired
    private UserLastNotificationReadJpaRepository userLastNotificationReadJpaRepository;

    @Override
    public UserLastNotificationRead get(Long id) {
        return userLastNotificationReadJpaRepository.getOne(id);
    }

    @Override
    public UserLastNotificationRead getByAuthUserId(Long id) {
        return userLastNotificationReadJpaRepository.findTop1ByAuthUserId(id);
    }

    @Override
    public UserLastNotificationRead save(UserLastNotificationRead userLastNotificationRead) {
        return userLastNotificationReadJpaRepository.saveAndFlush(userLastNotificationRead);
    }

    @Override
    public UserLastNotificationRead update(Long userLastNotificationReadId, UserLastNotificationRead userLastNotificationRead) {
        UserLastNotificationRead ulnr = userLastNotificationReadJpaRepository.getOne(userLastNotificationReadId);
        ulnr.setNotificationId(userLastNotificationRead.getNotificationId());
        return userLastNotificationReadJpaRepository.saveAndFlush(ulnr);
    }

    @Override
    public void delete(Long userLastNotificationReadId) {
        userLastNotificationReadJpaRepository.deleteById(userLastNotificationReadId);
    }
}
