package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.ProductInStateSegmentJpaRepository;
import com.wesdom.compa.backend.database.model.ProductInStateSegment;
import com.wesdom.compa.backend.database.repositories.IProductInStateSegmentRepository;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Service
public class ProductInStateSegmentRepositoryImpl implements IProductInStateSegmentRepository {
    
    @Autowired
    private ProductInStateSegmentJpaRepository productInStateSegmentJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public ProductInStateSegment get(Long id) {
        return productInStateSegmentJpaRepository.getOne(id);
    }

    @Override
    public Page<ProductInStateSegment> getAll(Map<String, String> params) {
        IPredicateBuilder<ProductInStateSegment> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return productInStateSegmentJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public ProductInStateSegment create(ProductInStateSegment productInStateSegment) {
        ProductInStateSegment p = productInStateSegmentJpaRepository.saveAndFlush(productInStateSegment);
        em.refresh(p);
        return p;
    }

    @Override
    public ProductInStateSegment update(Long productInStateSegmentId, ProductInStateSegment productInStateSegment) {
        ProductInStateSegment p ;//= productInStateSegmentJpaRepository.getOne(productInStateSegmentId);
//        p.setProduct(productInStateSegment.getProduct()).setEstateSegment(productInStateSegment.getEstateSegment())
//                .setArea(productInStateSegment.getArea()).setBeginning(productInStateSegment.getBeginning())
//                .setEnd(productInStateSegment.getEnd());
        p = productInStateSegmentJpaRepository.saveAndFlush(productInStateSegment);
        em.refresh(p);
        return  p;
    }

    @Override
    public void delete(Long productInStateSegmentId) {
        productInStateSegmentJpaRepository.deleteById(productInStateSegmentId);
    }

    @Override
    public List<ProductInStateSegment> getProductsByEstateSegmentId(Long estateSegmentId) {
        List<ProductInStateSegment> products = productInStateSegmentJpaRepository.getByEstateSegmentId(estateSegmentId);
        return products;
    }
}
