package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.ProductionActivityAnswerJpaRepository;
import com.wesdom.compa.backend.database.jparepositories.GroupTypeJpaRepository;
import com.wesdom.compa.backend.database.model.ProductionActivityAnswer;
import com.wesdom.compa.backend.database.repositories.IProductionActivityAnswerRepository;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;

@Service
public class ProductionActivityAnswerRepositoryImpl implements IProductionActivityAnswerRepository {
    
    @Autowired
    private ProductionActivityAnswerJpaRepository productionActivityAnswerJpaRepository;

    @Autowired
    private GroupTypeJpaRepository productionActivityAnswerTypeJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public ProductionActivityAnswer get(Long id) {
        return productionActivityAnswerJpaRepository.getOne(id);
    }

    @Override
    public Page<ProductionActivityAnswer> getAll(Map<String, String> params) {
        IPredicateBuilder<ProductionActivityAnswer> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return productionActivityAnswerJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public ProductionActivityAnswer save(ProductionActivityAnswer productionActivityAnswer) {
        productionActivityAnswer = productionActivityAnswerJpaRepository.saveAndFlush(productionActivityAnswer);
        em.refresh(productionActivityAnswer);
        return productionActivityAnswer;
    }

    @Override
    public ProductionActivityAnswer update(Long productionActivityAnswerId, ProductionActivityAnswer productionActivityAnswer) {
        ProductionActivityAnswer e = productionActivityAnswerJpaRepository.getOne(productionActivityAnswerId);
        e.setAnswer(productionActivityAnswer.getAnswer()).setActivityOption(productionActivityAnswer.getActivityOption())
                .setProductionActivity(productionActivityAnswer.getProductionActivity());
        e = productionActivityAnswerJpaRepository.saveAndFlush(e);
        em.refresh(e);
        return e;
    }

    @Override
    public void delete(Long productionActivityAnswerId) {
        productionActivityAnswerJpaRepository.deleteById(productionActivityAnswerId);
    }
}
