package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.ProductJpaRepository;
import com.wesdom.compa.backend.database.jparepositories.ProductTypeJpaRepository;
import com.wesdom.compa.backend.database.model.Product;
import com.wesdom.compa.backend.database.model.ProductType;
import com.wesdom.compa.backend.database.repositories.IProductRepository;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductRepositoryImpl implements IProductRepository {

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Autowired
    private ProductTypeJpaRepository productTypeJpaRepository;

    @Override
    public Product get(Long id) {
        return productJpaRepository.getOne(id);
    }

    @Override
    public Page<Product> getAll(Map<String, String> params) {
        IPredicateBuilder<Product> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return productJpaRepository.findAll(predicate.createPredicate(params), paginaton.createPagination(params));
    }

    @Override
    public Product save(Product product) {
        return productJpaRepository.save(product);
    }

    @Override
    public Product update(Long productId, Product product) {
        Product p = productJpaRepository.getOne(productId);
        p.setCode(product.getCode())
                .setAvProductivity(product.getAvProductivity())
                .setProductType(product.getProductType())
                .setName(product.getName())
                .setMeasureUnit(product.getMeasureUnit())
                .setSpecies(product.getSpecies())
                .setSeasonType(product.getSeasonType())
                .setHarvestMonths(product.getHarvestMonths());
        return productJpaRepository.save(p);
    }

    @Override
    public List<ProductType> getAllTypes() {
        return productTypeJpaRepository.findAll();
    }

    @Override
    public void delete(Long productId) {
        productJpaRepository.deleteById(productId);
    }

    @Override
    public Product findTop1ByMeasureUnitId(Long id) {
        return productJpaRepository.findTop1ByMeasureUnitId(id);
    }
}
