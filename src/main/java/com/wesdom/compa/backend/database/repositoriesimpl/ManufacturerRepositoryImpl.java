package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.ManufacturerJpaRepository;
import com.wesdom.compa.backend.database.jparepositories.ManufacturerTypeJpaRepository;
import com.wesdom.compa.backend.database.model.Manufacturer;
import com.wesdom.compa.backend.database.model.ManufacturerType;
import com.wesdom.compa.backend.database.repositories.IManufacturerRepository;
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
public class ManufacturerRepositoryImpl implements IManufacturerRepository {

    @Autowired
    private ManufacturerJpaRepository manufacturerJpaRepository;

    @Autowired
    private ManufacturerTypeJpaRepository manufacturerTypeJpaRepository;

    @Override
    public Manufacturer get(Long id) {
        return manufacturerJpaRepository.getOne(id);
    }

    @Override
    public Page<Manufacturer> getAll(Map<String, String> params) {
        IPredicateBuilder<Manufacturer> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return manufacturerJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        return manufacturerJpaRepository.save(manufacturer);
    }

    @Override
    public Manufacturer update(Long manufacturerId, Manufacturer manufacturer) {
        Manufacturer m = manufacturerJpaRepository.getOne(manufacturerId);
        m.setLatitude(manufacturer.getLatitude()).setLongitude(manufacturer.getLongitude())
                .setPlantation(manufacturer.getPlantation()).setType(manufacturer.getType()).setGender(manufacturer.getGender())
                .setFirstName(manufacturer.getFirstName()).setFirstLastname(manufacturer.getFirstLastname())
                .setSecondName(manufacturer.getSecondName()).setSecondLastName(manufacturer.getSecondLastName())
                .setBirthday(manufacturer.getBirthday()).setPhone(manufacturer.getPhone()).setDepartment(manufacturer.getDepartment())
                .setMunicipality(manufacturer.getMunicipality()).setGender(manufacturer.getGender())
                .setIdentificationType(manufacturer.getIdentificationType()).setIdentificationNumber(manufacturer.getIdentificationNumber());
        manufacturerJpaRepository.save(m);
        return m;
    }

    @Override
    public void delete(Long manufacturerId) {

    }

    @Override
    public List<ManufacturerType> getManufacturerTypeList() {
        return manufacturerTypeJpaRepository.findAll() ;
    }
}
