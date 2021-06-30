package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.AssociationJpaRepository;
import com.wesdom.compa.backend.database.model.Association;
import com.wesdom.compa.backend.database.repositories.IAssociationRepository;
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
public class AssociationRepositoryImpl implements IAssociationRepository {

    @Autowired
    private AssociationJpaRepository associationJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Association get(Long id) {
        return associationJpaRepository.getOne(id);
    }

    @Override
    public Page<Association> getAll(Map<String, String> params) {
        IPredicateBuilder<Association> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return associationJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public Association create(Association association) {
        Association m = associationJpaRepository.saveAndFlush(association);
        em.refresh(m);
        return m;
    }

    @Override
    public Association update(Long associationId, Association association) {
        Association m = associationJpaRepository.saveAndFlush(association);
        em.refresh(m);
        return m;
    }

    @Override
    public void delete(Long associationId) {
        
    }

}
