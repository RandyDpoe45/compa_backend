package com.wesdom.compa.backend.database.repositoriesimpl;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.database.jparepositories.CommercialPartnerJpaRepository;
import com.wesdom.compa.backend.database.model.users.CommercialPartner;
import com.wesdom.compa.backend.database.repositories.ICommercialPartnerRepository;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CommercialPartnerRepositoryImpl implements ICommercialPartnerRepository {

    @Autowired
    private CommercialPartnerJpaRepository commercialPartnerJpaRepository;

    @Override
    public CommercialPartner get(Long id) {
        return commercialPartnerJpaRepository.getOne(id);
    }

    @Override
    public Page<CommercialPartner> getAll(Map<String, String> params) {
        IPredicateBuilder<CommercialPartner> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return commercialPartnerJpaRepository.findAll(predicate.createPredicate(params), paginaton.createPagination(params));
    }

    @Override
    public CommercialPartner save(CommercialPartner commercialPartner) {
        return commercialPartnerJpaRepository.save(commercialPartner);
    }

    @Override
    public CommercialPartner update(Long commercialPartnerId, CommercialPartner commercialPartner) {
        CommercialPartner m = commercialPartnerJpaRepository.getOne(commercialPartnerId);
        m.setRut(commercialPartner.getRut()).setCompanyName(commercialPartner.getCompanyName())
                .setAccountNumber(commercialPartner.getAccountNumber())
                .setBank(commercialPartner.getBank())
                .setAccountType(commercialPartner.getAccountType())
                .setAccountNumber(commercialPartner.getAccountNumber())
                .setCamaraYComercio(commercialPartner.getCamaraYComercio())
                .setPersonType(commercialPartner.getPersonType())
                .setGender(commercialPartner.getGender())
                .setFirstName(commercialPartner.getFirstName())
                .setFirstLastname(commercialPartner.getFirstLastname())
                .setSecondName(commercialPartner.getSecondName())
                .setSecondLastName(commercialPartner.getSecondLastName())
                .setBirthday(commercialPartner.getBirthday())
                .setPhone(commercialPartner.getPhone())
                .setDepartment(commercialPartner.getDepartment())
                .setMunicipality(commercialPartner.getMunicipality())
                .setGender(commercialPartner.getGender())
                .setIdentificationType(commercialPartner.getIdentificationType())
                .setIdentificationNumber(commercialPartner.getIdentificationNumber());
        commercialPartnerJpaRepository.save(m);
        return m;
    }

    @Override
    public void delete(Long commercialPartnerId) {

    }
}
