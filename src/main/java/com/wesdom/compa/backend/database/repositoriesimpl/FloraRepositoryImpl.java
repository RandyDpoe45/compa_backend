package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.FloraJpaRepository;
import com.wesdom.compa.backend.database.model.Flora;
import com.wesdom.compa.backend.database.repositories.IFloraRepository;
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
public class FloraRepositoryImpl implements IFloraRepository {
    
    @Autowired
    private FloraJpaRepository floraJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Flora get(Long id) {
        return floraJpaRepository.getOne(id);
    }

    @Override
    public Page<Flora> getAll(Map<String, String> params) {
        IPredicateBuilder<Flora> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return floraJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Flora save(Flora flora) {
        flora = floraJpaRepository.saveAndFlush(flora);
        em.refresh(flora);
        return flora;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Flora update(Long estateSegmentId, Flora flora) {
        flora = floraJpaRepository.saveAndFlush(flora);
        em.refresh(flora);
        return flora;
    }

    @Override
    public void delete(Long estateSegmentId) {
        floraJpaRepository.deleteById(estateSegmentId);
    }
}
