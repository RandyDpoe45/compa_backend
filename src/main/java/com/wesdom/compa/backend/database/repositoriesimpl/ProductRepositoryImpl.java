package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.ProductJpaRepository;
import com.wesdom.compa.backend.database.model.Product;
import com.wesdom.compa.backend.database.repositories.IProductRepository;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductRepositoryImpl implements IProductRepository {
    
    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Override
    public Product get(Long id) {
        return productJpaRepository.getOne(id);
    }

    @Override
    public Page<Product> getAll(Map<String, String> params) {
        IPredicateBuilder<Product> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return productJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public Product create(Product product) {
        return productJpaRepository.save(product);
    }

    @Override
    public Product update(Long productId, Product product) {
        Product p = productJpaRepository.getOne(productId);
        p.setCode(product.getCode()).setAvProductivity(product.getAvProductivity())
                .setName(product.getName()).setMetricUnit(product.getMetricUnit());
        return  productJpaRepository.save(p);
    }

    @Override
    public void delete(Long productId) {
        productJpaRepository.deleteById(productId);
    }
}
