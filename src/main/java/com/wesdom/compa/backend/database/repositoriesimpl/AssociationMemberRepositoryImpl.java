package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.AssociationMemberJpaRepository;
import com.wesdom.compa.backend.database.model.AssociationMember;
import com.wesdom.compa.backend.database.repositories.IAssociationMemberRepository;
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
public class AssociationMemberRepositoryImpl implements IAssociationMemberRepository {

    @Autowired
    private AssociationMemberJpaRepository associationMemberJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public AssociationMember get(Long id) {
        return associationMemberJpaRepository.getOne(id);
    }

    @Override
    public Page<AssociationMember> getAll(Map<String, String> params) {
        IPredicateBuilder<AssociationMember> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return associationMemberJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public AssociationMember save(AssociationMember associationMember) {
        AssociationMember m = associationMemberJpaRepository.saveAndFlush(associationMember);
        em.refresh(m);
        return m;
    }

    @Override
    public AssociationMember update(Long associationMemberId, AssociationMember associationMember) {
        AssociationMember m = associationMemberJpaRepository.saveAndFlush(associationMember);
        em.refresh(m);
        return m;
    }

    @Override
    public AssociationMember getByAssociationIdAndManufacturerId(Long assId, Long manId) {
        return associationMemberJpaRepository
                .getByAssociationIdAndManufacturerId(assId,manId);
    }

    @Override
    public void delete(Long associationMemberId) {
        associationMemberJpaRepository.deleteById(associationMemberId);
    }

}
