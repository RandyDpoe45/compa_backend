package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.NearbyEstateSegmentJpaRepository;
import com.wesdom.compa.backend.database.model.AgriculturalSegment;
import com.wesdom.compa.backend.database.model.BeekeepingSegment;
import com.wesdom.compa.backend.database.model.NearbyEstateSegment;
import com.wesdom.compa.backend.database.repositories.INearbyEstateSegmentRepository;
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
public class NearbyEstateSegmentRepositoryImpl implements INearbyEstateSegmentRepository {
    
    @Autowired
    private NearbyEstateSegmentJpaRepository nearbyEstateSegmentJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public NearbyEstateSegment get(Long id) {
        return nearbyEstateSegmentJpaRepository.getOne(id);
    }

    @Override
    public Page<NearbyEstateSegment> getAll(Map<String, String> params) {
        IPredicateBuilder<NearbyEstateSegment> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return nearbyEstateSegmentJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public NearbyEstateSegment create(NearbyEstateSegment estateSegment) {
        estateSegment = nearbyEstateSegmentJpaRepository.saveAndFlush(estateSegment);
        em.refresh(estateSegment);
        return estateSegment;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public NearbyEstateSegment update(Long estateSegmentId, NearbyEstateSegment estateSegment) {
        estateSegment = nearbyEstateSegmentJpaRepository.saveAndFlush(estateSegment);
        em.refresh(estateSegment);
        return estateSegment;
    }

    @Override
    public void delete(Long estateSegmentId) {
        nearbyEstateSegmentJpaRepository.deleteById(estateSegmentId);
    }
}
