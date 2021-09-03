package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.GroupTypeJpaRepository;
import com.wesdom.compa.backend.database.jparepositories.EstateJpaRepository;
import com.wesdom.compa.backend.database.model.estatesegment.Estate;
import com.wesdom.compa.backend.database.repositories.IEstateRepository;
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
public class EstateRepositoryImpl  implements IEstateRepository {

    @Autowired
    private EstateJpaRepository estateJpaRepository;

    @Autowired
    private GroupTypeJpaRepository estateTypeJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Estate get(Long id) {
        return estateJpaRepository.getOne(id);
    }

    @Override
    public Page<Estate> getAll(Map<String, String> params) {
        IPredicateBuilder<Estate> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return estateJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public Estate save(Estate estate) {
        Estate e = estateJpaRepository.saveAndFlush(estate);
        em.refresh(e);
        return e;
    }

    @Override
    public Estate update(Long estateId, Estate estate) {
        Estate e = estateJpaRepository.getOne(estateId);
        e.setDepartment(estate.getDepartment()).setMunicipality(estate.getMunicipality()).setLatitude(estate.getLatitude())
                .setLongitude(estate.getLongitude()).setTotalArea(estate.getTotalArea()).setPlantation(estate.getPlantation())
                .setManufacturerGroup(estate.getManufacturerGroup()).setName(estate.getName());
        e = estateJpaRepository.saveAndFlush(e);
        em.refresh(e);
        return e;
    }

    @Override
    public void delete(Long estateId) {
        estateJpaRepository.deleteById(estateId);
    }
}
