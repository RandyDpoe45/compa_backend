package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.ProductionStageJpaRepository;
import com.wesdom.compa.backend.database.model.ProductionStage;
import com.wesdom.compa.backend.database.repositories.IProductionStageRepository;
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
public class ProductionStageRepositoryImpl implements IProductionStageRepository {
    
    @Autowired
    private ProductionStageJpaRepository productionStageJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public ProductionStage get(Long id) {
        return productionStageJpaRepository.getOne(id);
    }

    @Override
    public Page<ProductionStage> getAll(Map<String, String> params) {
        IPredicateBuilder<ProductionStage> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return productionStageJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ProductionStage create(ProductionStage productionStage) {
        productionStage = productionStageJpaRepository.saveAndFlush(productionStage);
        em.refresh(productionStage);
        return productionStage;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ProductionStage update(Long productionStageId, ProductionStage productionStage) {
        productionStage = productionStageJpaRepository.saveAndFlush(productionStage);
        em.refresh(productionStage);
        return productionStage;
    }

    @Override
    public void delete(Long productionStageId) {
        productionStageJpaRepository.deleteById(productionStageId);
    }
}
