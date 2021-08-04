package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.ExpertVisitNoteJpaRepository;
import com.wesdom.compa.backend.database.model.ExpertVisitNote;
import com.wesdom.compa.backend.database.model.ExpertVisitNote;
import com.wesdom.compa.backend.database.repositories.IExpertVisitNoteRepository;
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
public class ExpertVisitNoteRepositoryImpl implements IExpertVisitNoteRepository {
    
    @Autowired
    private ExpertVisitNoteJpaRepository expertVisitNoteJpaRepository;
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public ExpertVisitNote get(Long id) {
        return expertVisitNoteJpaRepository.getOne(id);
    }

    @Override
    public Page<ExpertVisitNote> getAll(Map<String, String> params) {
        IPredicateBuilder<ExpertVisitNote> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return expertVisitNoteJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ExpertVisitNote save(ExpertVisitNote expertVisitNote) {
        expertVisitNote = expertVisitNoteJpaRepository.saveAndFlush(expertVisitNote);
        em.refresh(expertVisitNote);
        return expertVisitNote;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ExpertVisitNote update(Long estateSegmentId, ExpertVisitNote expertVisitNote) {
        expertVisitNote = expertVisitNoteJpaRepository.saveAndFlush(expertVisitNote);
        em.refresh(expertVisitNote);
        return expertVisitNote;
    }

    @Override
    public void delete(Long estateSegmentId) {
        expertVisitNoteJpaRepository.deleteById(estateSegmentId);
    }
}
