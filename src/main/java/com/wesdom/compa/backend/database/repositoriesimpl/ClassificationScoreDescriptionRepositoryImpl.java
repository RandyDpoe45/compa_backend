package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.ClassificationScoreDescriptionJpaRepository;
import com.wesdom.compa.backend.database.model.ClassificationScore;
import com.wesdom.compa.backend.database.model.ClassificationScoreDescription;
import com.wesdom.compa.backend.database.repositories.IClassificationScoreDescriptionRepository;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Service
public class ClassificationScoreDescriptionRepositoryImpl implements IClassificationScoreDescriptionRepository {

    @Autowired
    private ClassificationScoreDescriptionJpaRepository classificationScoreDescriptionJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public ClassificationScoreDescription get(Long id) {
        return classificationScoreDescriptionJpaRepository.getOne(id);
    }

    @Override
    public Page<ClassificationScoreDescription> getAll(Map<String, String> params) {
            IPredicateBuilder<ClassificationScoreDescription> predicate = new PredicateBuilderServiceImpl<>();
            IPaginationBuilder paginaton = new PaginationBuilderImpl();
            return classificationScoreDescriptionJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public ClassificationScoreDescription getClassificationScoreBetweenRangeAndEntityName(Float score, String entityName) {
        List<ClassificationScoreDescription> classificationScoreList =
                classificationScoreDescriptionJpaRepository.findByScoreInRangeAndEntityName(
                        score,
                        entityName,
                        PageRequest.of(0,1)
                );
        return classificationScoreList.isEmpty() ? null : classificationScoreList.get(0);
    }

    @Override
    public ClassificationScoreDescription save(ClassificationScoreDescription classificationScore) {
        classificationScore = classificationScoreDescriptionJpaRepository.saveAndFlush(classificationScore);
        em.refresh(classificationScore);
        return classificationScore;
    }

    @Override
    public ClassificationScoreDescription update(Long classificationScoreId, ClassificationScoreDescription classificationScore) {
        ClassificationScoreDescription csd = classificationScoreDescriptionJpaRepository.getOne(classificationScoreId);
        csd.setDescription(classificationScore.getDescription())
                .setClassificationScore(classificationScore.getClassificationScore())
                .setEntityName(classificationScore.getEntityName());
        csd = classificationScoreDescriptionJpaRepository.saveAndFlush(csd);
        em.refresh(csd);
        return csd;
    }

    @Override
    public void delete(Long classificationScoreId) {
        classificationScoreDescriptionJpaRepository.deleteById(classificationScoreId);
    }
}
