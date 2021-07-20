package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.AuthUserJpaRepository;
import com.wesdom.compa.backend.database.model.AuthUser;
import com.wesdom.compa.backend.database.repositories.IAuthUserRepository;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthUserRepositoryImpl implements IAuthUserRepository {

    @Autowired
    private AuthUserJpaRepository authUserJpaRepository;

    @Override
    public AuthUser get(Long id) {
        return authUserJpaRepository.getOne(id);
    }

    @Override
    public Page<AuthUser> getAll(Map<String, String> params) {
        IPredicateBuilder<AuthUser> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder pagination = new PaginationBuilderImpl();
        return authUserJpaRepository.findAll(predicate.createPredicate(params),pagination.createPagination(params));
    }

    @Override
    public AuthUser create(AuthUser authUser) {
        return authUserJpaRepository.saveAndFlush(authUser);
    }

    @Override
    public AuthUser update(Long userId, AuthUser authUser) {
        AuthUser user = authUserJpaRepository.getOne(userId);
        user.setEmail(authUser.getEmail()).setUsername(authUser.getUsername());
        return user;
    }

    @Override
    public AuthUser updatePassword(Long userId, String newPassword) {
        AuthUser authUser = authUserJpaRepository.getOne(userId);
        authUser.setPassword(newPassword);
        authUserJpaRepository.save(authUser);
        return authUserJpaRepository.save(authUser);
    }

    @Override
    public AuthUser getUserByUserNameOrEmail(String text) {
        return authUserJpaRepository.getAuthUserByUsernameOrEmail(text);
    }
}
