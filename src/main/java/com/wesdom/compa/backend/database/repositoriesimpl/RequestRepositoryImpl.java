package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.RequestJpaRepository;
import com.wesdom.compa.backend.database.model.Request;
import com.wesdom.compa.backend.database.repositories.IRequestRepository;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;

@Service
public class RequestRepositoryImpl implements IRequestRepository {

    @Autowired
    private RequestJpaRepository requestJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Request get(Long id) {
        return requestJpaRepository.getOne(id);
    }

    @Override
    public Page<Request> getAll(Map<String, String> params) {
        IPredicateBuilder<Request> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return requestJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public Request save(Request request) {
        request = requestJpaRepository.saveAndFlush(request);
        em.refresh(request);
        return request;
    }

    @Override
    public Request update(Long requestId, Request request) {
        request = requestJpaRepository.saveAndFlush(request);
        em.refresh(request);
        return  request;
    }

    @Override
    public void delete(Long requestId) {
        requestJpaRepository.deleteById(requestId);
    }

    @Override
    public Request findTop1ByProductId(Long id) {
        return requestJpaRepository.findTop1ByProductId(id);
    }
}
