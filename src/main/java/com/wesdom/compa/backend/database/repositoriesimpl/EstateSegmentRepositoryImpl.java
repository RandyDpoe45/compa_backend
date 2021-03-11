package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.EstateSegmentTypeJpaRepository;
import com.wesdom.compa.backend.database.jparepositories.EstateSegmentJpaRepository;
import com.wesdom.compa.backend.database.model.EstateSegment;
import com.wesdom.compa.backend.database.model.EstateSegmentType;
import com.wesdom.compa.backend.database.model.EstateSegmentType;
import com.wesdom.compa.backend.database.model.EstateSegment;
import com.wesdom.compa.backend.database.repositories.IEstateSegmentRepository;
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
public class EstateSegmentRepositoryImpl implements IEstateSegmentRepository {

    @Autowired
    private EstateSegmentJpaRepository estateSegmentJpaRepository;

    @Autowired
    private EstateSegmentTypeJpaRepository estateSegmentTypeJpaRepository;

    @Override
    public EstateSegment get(Long id) {
        return estateSegmentJpaRepository.getOne(id);
    }

    @Override
    public Page<EstateSegment> getAll(Map<String, String> params) {
        IPredicateBuilder<EstateSegment> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return estateSegmentJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public EstateSegment create(EstateSegment estateSegment) {
        return estateSegmentJpaRepository.save(estateSegment);
    }

    @Override
    public EstateSegment update(Long estateSegmentId, EstateSegment estateSegment) {
        EstateSegment e = estateSegmentJpaRepository.getOne(estateSegmentId);
        e.setArea(estateSegment.getArea()).setEstate(estateSegment.getEstate()).setEstateSegmentType(estateSegment.getEstateSegmentType());
        estateSegmentJpaRepository.save(e);
        return e;
    }

    @Override
    public void delete(Long estateSegmentId) {
        estateSegmentJpaRepository.deleteById(estateSegmentId);
    }

    @Override
    public List<EstateSegmentType> getEstateSegmentTypeList() {
        return estateSegmentTypeJpaRepository.findAll();
    }
}
