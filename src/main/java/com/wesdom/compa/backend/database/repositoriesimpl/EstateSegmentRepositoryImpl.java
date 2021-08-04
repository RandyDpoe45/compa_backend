package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.*;
import com.wesdom.compa.backend.database.model.*;
import com.wesdom.compa.backend.database.model.EstateSegmentType;
import com.wesdom.compa.backend.database.model.EstateSegment;
import com.wesdom.compa.backend.database.repositories.IEstateSegmentRepository;
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
public class EstateSegmentRepositoryImpl implements IEstateSegmentRepository {

    @Autowired
    private EstateSegmentJpaRepository estateSegmentJpaRepository;

    @Autowired
    private EstateSegmentTypeJpaRepository estateSegmentTypeJpaRepository;

    @Autowired
    private BeekeepingJpaRepository beekeepingJpaRepository;

    @Autowired
    private AgriculturalJpaRepository agriculturalJpaRepository;

    @Autowired
    private ConservationJpaRepository conservationJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public EstateSegment get(Long id) {
        return estateSegmentJpaRepository.findById(id).get();
    }

    @Override
    public Page<EstateSegment> getAll(Map<String, String> params) {
        IPredicateBuilder<EstateSegment> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return estateSegmentJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public Page<BeekeepingSegment> getAllBe(Map<String, String> params) {
        IPredicateBuilder<BeekeepingSegment> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return beekeepingJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public Page<AgriculturalSegment> getAllAg(Map<String, String> params) {
        IPredicateBuilder<AgriculturalSegment> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return agriculturalJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public Page<ConservationSegment> getAllCo(Map<String, String> params) {
        IPredicateBuilder<ConservationSegment> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return conservationJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public EstateSegment save(EstateSegment estateSegment) {
        estateSegment = estateSegmentJpaRepository.saveAndFlush(estateSegment);
        em.refresh(estateSegment);
        return estateSegment;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public EstateSegment update(Long estateSegmentId, EstateSegment estateSegment) {
        estateSegment = estateSegmentJpaRepository.saveAndFlush(estateSegment);
        em.refresh(estateSegment);
        return estateSegment;
    }

    @Override
    public void delete(Long estateSegmentId) {
        estateSegmentJpaRepository.deleteById(estateSegmentId);
    }

    @Override
    public List<EstateSegmentType> getEstateSegmentTypeList() {
        return estateSegmentTypeJpaRepository.findAll();
    }
}
