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

import java.util.Map;

@Service
public class ProductionActivityRepositoryImpl implements IProductionActivityRepository {

    @Autowired
    private ProductionActivityJpaRepository productionActivityJpaRepository;

    @Autowired
    private GroupTypeJpaRepository productionActivityTypeJpaRepository;

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
    public ProductionActivity create(ProductionActivity productionActivity) {
        return productionActivityJpaRepository.save(productionActivity);
    }

    @Override
    public ProductionActivity update(Long productionActivityId, ProductionActivity productionActivity) {
        ProductionActivity e = productionActivityJpaRepository.getOne(productionActivityId);
        e.setActivity(productionActivity.getActivity()).setActivityAnswers(productionActivity.getActivityAnswers())
                .setProductInStateSegment(productionActivity.getProductInStateSegment());
        productionActivityJpaRepository.save(e);
        return e;
    }

    @Override
    public void delete(Long productionActivityId) {
        productionActivityJpaRepository.deleteById(productionActivityId);
    }
}
