package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.ClassificationScoreJpaRepository;
import com.wesdom.compa.backend.database.model.ClassificationScore;
import com.wesdom.compa.backend.database.model.CommercialPartner;
import com.wesdom.compa.backend.database.repositories.IClassificationScoreRepository;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClassificationRepositoryImpl implements IClassificationScoreRepository {

    @Autowired
    private ClassificationScoreJpaRepository classificationScoreJpaRepository;

    @Override
    public ClassificationScore get(Long id) {
        return classificationScoreJpaRepository.getOne(id);
    }

    @Override
    public Page<ClassificationScore> getAll(Map<String, String> params) {
        IPredicateBuilder<ClassificationScore> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return classificationScoreJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public ClassificationScore getClassificationScoreBetweenRange(Float score) {
        List<ClassificationScore> classificationScoreList =
                classificationScoreJpaRepository.findByScoreInRange(
                        score,
                        PageRequest.of(0,1)
                );
        return classificationScoreList.isEmpty() ? null : classificationScoreList.get(0);
    }

    @Override
    public ClassificationScore save(ClassificationScore classificationScore) {
        return classificationScoreJpaRepository.saveAndFlush(classificationScore);
    }

    @Override
    public ClassificationScore update(Long classificationScoreId, ClassificationScore classificationScore) {
        return classificationScoreJpaRepository.saveAndFlush(classificationScore);
    }

    @Override
    public void delete(Long classificationScoreId) {
        classificationScoreJpaRepository.deleteById(classificationScoreId);
    }
}
