package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.ActivityJpaRepository;
import com.wesdom.compa.backend.database.model.Activity;
import com.wesdom.compa.backend.database.repositories.IActivityRepository;
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
import java.util.Map;

@Service
public class ActivityRepositoryImpl implements IActivityRepository {

    @Autowired
    private ActivityJpaRepository activityJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Activity get(Long id) {
        return activityJpaRepository.getOne(id);
    }

    @Override
    public Page<Activity> getAll(Map<String, String> params) {
        IPredicateBuilder<Activity> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return activityJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    @Transactional
    public Activity create(Activity activity) {
        Activity a = activityJpaRepository.saveAndFlush(activity);
        em.refresh(a);
        return a;
    }

    @Override
    @Transactional
    public Activity update(Long activityId, Activity activity) {
        Activity a = null;//activityJpaRepository.getOne(activityId);
//        a.setActivityOptionList(activity.getActivityOptionList()).setName(activity.getName())
//                .setStage(activity.getStage());
        a = activityJpaRepository.saveAndFlush(activity);
        em.refresh(a);
        return  a;
    }

    @Override
    public void delete(Long activityId) {
        activityJpaRepository.deleteById(activityId);
    }
}
