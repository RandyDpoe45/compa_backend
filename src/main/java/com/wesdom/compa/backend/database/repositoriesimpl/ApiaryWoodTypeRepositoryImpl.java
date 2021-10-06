package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.ApiaryWoodTypeJpaRepository;
import com.wesdom.compa.backend.database.model.estatesegment.ApiaryWoodType;
import com.wesdom.compa.backend.database.repositories.IApiaryWoodTypeRepository;
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
public class ApiaryWoodTypeRepositoryImpl implements IApiaryWoodTypeRepository {
    
    @Autowired
    private ApiaryWoodTypeJpaRepository apiaryWoodTypeJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public ApiaryWoodType get(Long id) {
        return apiaryWoodTypeJpaRepository.getOne(id);
    }

    @Override
    public Page<ApiaryWoodType> getAll(Map<String, String> params) {
        IPredicateBuilder<ApiaryWoodType> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return apiaryWoodTypeJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public ApiaryWoodType save(ApiaryWoodType apiaryWoodType) {
        apiaryWoodType = apiaryWoodTypeJpaRepository.saveAndFlush(apiaryWoodType);
        em.refresh(apiaryWoodType);
        return apiaryWoodType;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public ApiaryWoodType update(Long estateSegmentId, ApiaryWoodType apiaryWoodType) {
        apiaryWoodType = apiaryWoodTypeJpaRepository.saveAndFlush(apiaryWoodType);
        em.refresh(apiaryWoodType);
        return apiaryWoodType;
    }

    @Override
    public void delete(Long estateSegmentId) {
        apiaryWoodTypeJpaRepository.deleteById(estateSegmentId);
    }
}
