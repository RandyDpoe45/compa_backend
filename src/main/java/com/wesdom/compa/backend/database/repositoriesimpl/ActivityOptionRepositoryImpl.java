package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.ActivityOptionJpaRepository;
import com.wesdom.compa.backend.database.model.ActivityOption;
import com.wesdom.compa.backend.database.repositories.IActivityOptionRepository;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Service
public class ActivityOptionRepositoryImpl implements IActivityOptionRepository {

    @Autowired
    private ActivityOptionJpaRepository activityOptionJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public ActivityOption get(Long id) {
        return activityOptionJpaRepository.getOne(id);
    }

    @Override
    public List<ActivityOption> findById(List<Long> ids) {
        return activityOptionJpaRepository.findAllById(ids);
    }

    @Override
    public Page<ActivityOption> getAll(Map<String, String> params) {
        IPredicateBuilder<ActivityOption> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return activityOptionJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    @Transactional
    public ActivityOption save(ActivityOption activityOption) {
        ActivityOption a = activityOptionJpaRepository.saveAndFlush(activityOption);
        em.refresh(a);
        return a;
    }

    @Override
    @Transactional
    public ActivityOption update(Long activityOptionId, ActivityOption activityOption) {
        ActivityOption a = null;//activityOptionJpaRepository.getOne(activityOptionId);
//        a.setName(activityOption.getName()).setOptionType(activityOption.getOptionType())
//                .setOptionAnswersList(activityOption.getOptionAnswersList()).setAnswerType(activityOption.getAnswerType());
        a = activityOptionJpaRepository.saveAndFlush(activityOption);
        em.refresh(a);
        return  a;
    }

    @Override
    public void delete(Long activityOptionId) {
        activityOptionJpaRepository.deleteById(activityOptionId);
    }
}
