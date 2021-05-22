package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.DepartmentJpaRepository;
import com.wesdom.compa.backend.database.jparepositories.MunicipalityJpaRepository;
import com.wesdom.compa.backend.database.model.Department;
import com.wesdom.compa.backend.database.model.Municipality;
import com.wesdom.compa.backend.database.repositories.ILocationRepository;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LocationRepositoryImpl implements ILocationRepository {

    @Autowired
    private DepartmentJpaRepository departmentJpaRepository;

    @Autowired
    private MunicipalityJpaRepository municipalityJpaRepository;


    @Override
    public Page<Department> getAllDepartments(Map<String, String> params) {
        IPaginationBuilder paginaiton = new PaginationBuilderImpl();
        IPredicateBuilder<Department> predicate = new PredicateBuilderServiceImpl<>();
        return departmentJpaRepository.findAll(predicate.createPredicate(params),paginaiton.createPagination(params));
    }

    @Override
    public Page<Municipality> getAllMunicipalities(Map<String, String> params) {
        IPaginationBuilder paginaiton = new PaginationBuilderImpl();
        IPredicateBuilder<Municipality> predicate = new PredicateBuilderServiceImpl<>();
        return municipalityJpaRepository.findAll(predicate.createPredicate(params),paginaiton.createPagination(params));
    }
}
