package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.AssociationPromoterJpaRepository;
import com.wesdom.compa.backend.database.model.AssociationPromoter;
import com.wesdom.compa.backend.database.repositories.IAssociationPromoterRepository;
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
public class AssociationPromoterRepositoryImpl implements IAssociationPromoterRepository {

    @Autowired
    private AssociationPromoterJpaRepository associationPromoterJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public AssociationPromoter get(Long id) {
        return associationPromoterJpaRepository.getOne(id);
    }

    @Override
    public Page<AssociationPromoter> getAll(Map<String, String> params) {
        IPredicateBuilder<AssociationPromoter> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return associationPromoterJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public AssociationPromoter create(AssociationPromoter associationPromoter) {
        AssociationPromoter m = associationPromoterJpaRepository.saveAndFlush(associationPromoter);
        em.refresh(m);
        return m;
    }

    @Override
    public AssociationPromoter update(Long associationPromoterId, AssociationPromoter associationPromoter) {
        AssociationPromoter m = associationPromoterJpaRepository.saveAndFlush(associationPromoter);
        em.refresh(m);
        return m;
    }

    @Override
    public AssociationPromoter getByAssociationIdAndPromoterId(Long assId, Long promId) {
        return associationPromoterJpaRepository
                .getByAssociationIdAndPromoterId(assId,promId);
    }

    @Override
    public void delete(Long associationPromoterId) {
        associationPromoterJpaRepository.deleteById(associationPromoterId);
    }

}
