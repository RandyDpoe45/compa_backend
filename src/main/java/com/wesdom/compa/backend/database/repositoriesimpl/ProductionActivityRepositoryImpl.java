package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.ProductionActivityJpaRepository;
import com.wesdom.compa.backend.database.jparepositories.GroupTypeJpaRepository;
import com.wesdom.compa.backend.database.model.ProductionActivity;
import com.wesdom.compa.backend.database.repositories.IProductionActivityRepository;
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
public class ProductionActivityRepositoryImpl implements IProductionActivityRepository {

    @Autowired
    private ProductionActivityJpaRepository productionActivityJpaRepository;

    @Autowired
    private GroupTypeJpaRepository productionActivityTypeJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public ProductionActivity get(Long id) {
        return productionActivityJpaRepository.getOne(id);
    }

    @Override
    public Page<ProductionActivity> getAll(Map<String, String> params) {
        IPredicateBuilder<ProductionActivity> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return productionActivityJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ProductionActivity create(ProductionActivity productionActivity) {
        productionActivity = productionActivityJpaRepository.saveAndFlush(productionActivity);
        em.refresh(productionActivity);
        return productionActivity;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ProductionActivity update(Long productionActivityId, ProductionActivity productionActivity) {
        ProductionActivity e = productionActivityJpaRepository.getOne(productionActivityId);
        e.setActivity(productionActivity.getActivity()).setActivityAnswers(productionActivity.getActivityAnswers())
                .setProductInStateSegment(productionActivity.getProductInStateSegment());
        e = productionActivityJpaRepository.saveAndFlush(e);
        em.refresh(e);
        return e;
    }

    @Override
    public void delete(Long productionActivityId) {
        productionActivityJpaRepository.deleteById(productionActivityId);
    }

    @Override
    public ProductionActivity findTop1ByProductInStateSegmentId(Long id) {
        return productionActivityJpaRepository.findTop1ByProductInStateSegmentId(id);
    }
}
