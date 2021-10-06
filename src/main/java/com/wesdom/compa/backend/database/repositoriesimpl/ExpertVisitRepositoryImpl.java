package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.ExpertVisitJpaRepository;
import com.wesdom.compa.backend.database.jparepositories.ExpertVisitTypeJpaRepository;
import com.wesdom.compa.backend.database.model.ExpertVisit;
import com.wesdom.compa.backend.database.model.ExpertVisitType;
import com.wesdom.compa.backend.database.repositories.IExpertVisitRepository;
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
public class ExpertVisitRepositoryImpl implements IExpertVisitRepository {

    @Autowired
    private ExpertVisitJpaRepository expertVisitJpaRepository;

    @Autowired
    private ExpertVisitTypeJpaRepository expertVisitTypeJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public ExpertVisit get(Long id) {
        return expertVisitJpaRepository.getOne(id);
    }

    @Override
    public Page<ExpertVisit> getAll(Map<String, String> params) {
        IPredicateBuilder<ExpertVisit> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return expertVisitJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public ExpertVisit save(ExpertVisit expertVisit) {
        expertVisit = expertVisitJpaRepository.saveAndFlush(expertVisit);
        em.refresh(expertVisit);
        return expertVisit;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public ExpertVisit update(Long estateSegmentId, ExpertVisit expertVisit) {
        expertVisit = expertVisitJpaRepository.saveAndFlush(expertVisit);
        em.refresh(expertVisit);
        return expertVisit;
    }

    @Override
    public List<ExpertVisitType> getTypes() {
        return expertVisitTypeJpaRepository.findAll();
    }

    @Override
    public void delete(Long estateSegmentId) {
        expertVisitJpaRepository.deleteById(estateSegmentId);
    }
}
