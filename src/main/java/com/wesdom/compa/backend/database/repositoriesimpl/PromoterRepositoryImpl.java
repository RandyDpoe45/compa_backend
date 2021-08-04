package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.PromoterJpaRepository;
import com.wesdom.compa.backend.database.model.Promoter;
import com.wesdom.compa.backend.database.repositories.IPromoterRepository;
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
public class PromoterRepositoryImpl implements IPromoterRepository {

    @Autowired
    private PromoterJpaRepository promoterJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Promoter get(Long id) {
        return promoterJpaRepository.getOne(id);
    }

    @Override
    public Page<Promoter> getAll(Map<String, String> params) {
        IPredicateBuilder<Promoter> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return promoterJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public Promoter save(Promoter promoter) {
        promoter = promoterJpaRepository.saveAndFlush(promoter);
        em.refresh(promoter);
        return promoter;
    }

    @Override
    public Promoter update(Long promoterId, Promoter promoter) {
        Promoter m = promoterJpaRepository.getOne(promoterId);
        m.setLatitude(promoter.getLatitude()).setLongitude(promoter.getLongitude())
            .setPlantation(promoter.getPlantation()).setType(promoter.getType()).setGender(promoter.getGender())
            .setFirstName(promoter.getFirstName()).setFirstLastname(promoter.getFirstLastname())
            .setSecondName(promoter.getSecondName()).setSecondLastName(promoter.getSecondLastName())
            .setBirthday(promoter.getBirthday()).setPhone(promoter.getPhone()).setDepartment(promoter.getDepartment())
            .setMunicipality(promoter.getMunicipality()).setGender(promoter.getGender())
            .setIdentificationType(promoter.getIdentificationType()).setIdentificationNumber(promoter.getIdentificationNumber());
        m.setMorning(promoter.getMorning()).setAfternoon(promoter.getAfternoon())
                .setAvailableDays(promoter.getAvailableDays()).setMotorcycle(promoter.getMotorcycle())
                .setIntroduction(promoter.getIntroduction()).setManufacturerGroupList(promoter.getManufacturerGroupList())
                .setSystemBioProductList(promoter.getSystemBioProductList());
        m = promoterJpaRepository.saveAndFlush(m);
        em.refresh(m);
        return m;
    }

    @Override
    public void delete(Long promoterId) {

    }
}
