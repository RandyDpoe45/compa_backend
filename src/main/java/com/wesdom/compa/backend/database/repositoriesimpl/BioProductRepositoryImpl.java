package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.BioProductJpaRepository;
import com.wesdom.compa.backend.database.model.BioProduct;
import com.wesdom.compa.backend.database.repositories.IBioProductRepository;
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
public class BioProductRepositoryImpl implements IBioProductRepository {
    
    @Autowired
    private BioProductJpaRepository bioProductJpaRepository;

    @Override
    public BioProduct get(Long id) {
        return bioProductJpaRepository.getOne(id);
    }

    @Override
    public Page<BioProduct> getAll(Map<String, String> params) {
        IPredicateBuilder<BioProduct> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return bioProductJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public BioProduct save(BioProduct bioProduct) {
        return bioProductJpaRepository.save(bioProduct);
    }

    @Override
    public BioProduct update(Long bioProductId, BioProduct bioProduct) {
        return  bioProductJpaRepository.saveAndFlush(bioProduct);
    }

    @Override
    public void delete(Long bioProductId) {
        bioProductJpaRepository.deleteById(bioProductId);
    }

    @Override
    public List<BioProduct> findAllById(List<Long> ids) {
        return bioProductJpaRepository.findAllById(ids);
    }
}
