package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.RequestOfferJpaRepository;
import com.wesdom.compa.backend.database.jparepositories.RequestOfferStatusJpaRepository;
import com.wesdom.compa.backend.database.model.RequestOffer;
import com.wesdom.compa.backend.database.model.RequestOfferStatus;
import com.wesdom.compa.backend.database.repositories.IRequestOfferRepository;
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
public class RequestOfferRepositoryImpl implements IRequestOfferRepository {

    @Autowired
    private RequestOfferJpaRepository requestOfferJpaRepository;

    @Autowired
    private RequestOfferStatusJpaRepository requestOfferStatusJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public RequestOffer get(Long id) {
        return requestOfferJpaRepository.getOne(id);
    }

    @Override
    public Page<RequestOffer> getAll(Map<String, String> params) {
        IPredicateBuilder<RequestOffer> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return requestOfferJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public RequestOffer save(RequestOffer requestOffer) {
        requestOffer = requestOfferJpaRepository.saveAndFlush(requestOffer);
        em.refresh(requestOffer);
        return requestOffer;
    }

    @Override
    public List<RequestOfferStatus> getStatusList() {
        return requestOfferStatusJpaRepository.findAll();
    }

    @Override
    public RequestOfferStatus getStatusByCode(String id) {
        return requestOfferStatusJpaRepository.findTop1ByCode(id);
    }

    @Override
    public RequestOffer update(Long requestOfferId, RequestOffer requestOffer) {
        requestOffer = requestOfferJpaRepository.saveAndFlush(requestOffer);
        em.refresh(requestOffer);
        return  requestOffer;
    }

    @Override
    public void delete(Long requestOfferId) {
        requestOfferJpaRepository.deleteById(requestOfferId);
    }
}
