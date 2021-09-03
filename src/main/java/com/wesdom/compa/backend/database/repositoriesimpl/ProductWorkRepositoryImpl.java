package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.ProductWorkJpaRepository;
import com.wesdom.compa.backend.database.model.estatesegment.ProductWork;
import com.wesdom.compa.backend.database.repositories.IProductWorkRepository;
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
public class ProductWorkRepositoryImpl implements IProductWorkRepository {
    
    @Autowired
    private ProductWorkJpaRepository productWorkJpaRepository;

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public ProductWork get(Long id) {
        return productWorkJpaRepository.getOne(id);
    }

    @Override
    public Page<ProductWork> getAll(Map<String, String> params) {
        IPredicateBuilder<ProductWork> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return productWorkJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public ProductWork save(ProductWork product) {
        product = productWorkJpaRepository.saveAndFlush(product);
        em.refresh(product);
        return product;
    }

    @Override
    public ProductWork update(Long productId, ProductWork product) {
        product = productWorkJpaRepository.saveAndFlush(product);
        em.refresh(product);
        return product;
    }

    @Override
    public void delete(Long productId) {
        productWorkJpaRepository.deleteById(productId);
    }
}
