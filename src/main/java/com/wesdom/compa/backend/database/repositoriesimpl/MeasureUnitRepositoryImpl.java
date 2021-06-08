package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.MeasureUnitJpaRepository;
import com.wesdom.compa.backend.database.model.MeasureUnit;
import com.wesdom.compa.backend.database.repositories.IMeasureUnitRepository;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MeasureUnitRepositoryImpl implements IMeasureUnitRepository {
    
    @Autowired
    private MeasureUnitJpaRepository measureUnitJpaRepository;

    @Override
    public MeasureUnit get(Long id) {
        return measureUnitJpaRepository.getOne(id);
    }

    @Override
    public Page<MeasureUnit> getAll(Map<String, String> params) {
        IPredicateBuilder<MeasureUnit> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return measureUnitJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public MeasureUnit create(MeasureUnit measureUnit) {
        return measureUnitJpaRepository.save(measureUnit);
    }

    @Override
    public MeasureUnit update(Long manufacturerId, MeasureUnit measureUnit) {
        MeasureUnit m =measureUnitJpaRepository.saveAndFlush(measureUnit);
        return m;
    }

    @Override
    public void delete(Long measureUnitId) {
        measureUnitJpaRepository.deleteById(measureUnitId);
    }
}
