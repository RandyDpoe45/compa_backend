package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.ExpertAlertJpaRepository;
import com.wesdom.compa.backend.database.model.ExpertAlert;
import com.wesdom.compa.backend.database.repositories.IExpertAlertRepository;
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
public class ExpertAlertRepositoryImpl implements IExpertAlertRepository {

    @Autowired
    private ExpertAlertJpaRepository expertAlertJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public ExpertAlert get(Long id) {
        return expertAlertJpaRepository.getOne(id);
    }

    @Override
    public Page<ExpertAlert> getAll(Map<String, String> params) {
        IPredicateBuilder<ExpertAlert> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return expertAlertJpaRepository.findAll(predicate.createPredicate(params), paginaton.createPagination(params));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public ExpertAlert save(ExpertAlert expertAlert) {
        expertAlert = expertAlertJpaRepository.saveAndFlush(expertAlert);
        em.refresh(expertAlert);
        return expertAlert;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public ExpertAlert update(Long estateSegmentId, ExpertAlert expertAlert) {
        expertAlert = expertAlertJpaRepository.saveAndFlush(expertAlert);
        em.refresh(expertAlert);
        return expertAlert;
    }


    @Override
    public void delete(Long estateSegmentId) {
        expertAlertJpaRepository.deleteById(estateSegmentId);
    }
}
