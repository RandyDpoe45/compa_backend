package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.AdvertisingJpaRepository;
import com.wesdom.compa.backend.database.model.Advertising;
import com.wesdom.compa.backend.database.repositories.IAdvertisingRepository;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Service
public class AdvertisingRepositoryImpl implements IAdvertisingRepository {
    
    @Autowired
    private AdvertisingJpaRepository advertisingJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Advertising get(Long id) {
        return advertisingJpaRepository.getOne(id);
    }

    @Override
    public Page<Advertising> getAll(Map<String, String> params) {
        IPredicateBuilder<Advertising> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return advertisingJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public Advertising save(Advertising advertising) {
        Advertising a = advertisingJpaRepository.saveAndFlush(advertising);
        em.refresh(a);
        return a;
    }

    @Override
    public Advertising update(Long advertisingId, Advertising advertising) {
        Advertising a = advertisingJpaRepository.saveAndFlush(advertising);
        em.refresh(a);
        return  a;
    }

    @Override
    public void delete(Long advertisingId) {
        advertisingJpaRepository.deleteById(advertisingId);
    }
}
