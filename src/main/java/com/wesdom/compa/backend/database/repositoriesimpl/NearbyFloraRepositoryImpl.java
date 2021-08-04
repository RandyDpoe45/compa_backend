package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.NearbyFloraJpaRepository;
import com.wesdom.compa.backend.database.model.NearbyFlora;
import com.wesdom.compa.backend.database.repositories.INearbyFloraRepository;
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
public class NearbyFloraRepositoryImpl implements INearbyFloraRepository {
    
    @Autowired
    private NearbyFloraJpaRepository nearbyFloraJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public NearbyFlora get(Long id) {
        return nearbyFloraJpaRepository.getOne(id);
    }

    @Override
    public Page<NearbyFlora> getAll(Map<String, String> params) {
        IPredicateBuilder<NearbyFlora> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return nearbyFloraJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public NearbyFlora save(NearbyFlora nearbyFlora) {
        nearbyFlora = nearbyFloraJpaRepository.saveAndFlush(nearbyFlora);
        em.refresh(nearbyFlora);
        return nearbyFlora;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public NearbyFlora update(Long estateSegmentId, NearbyFlora nearbyFlora) {
        nearbyFlora = nearbyFloraJpaRepository.saveAndFlush(nearbyFlora);
        em.refresh(nearbyFlora);
        return nearbyFlora;
    }

    @Override
    public void delete(Long estateSegmentId) {
        nearbyFloraJpaRepository.deleteById(estateSegmentId);
    }

    @Override
    public NearbyFlora findTop1ByFloraId(Long id) {
        return nearbyFloraJpaRepository.findTop1ByFloraId(id);
    }
}
