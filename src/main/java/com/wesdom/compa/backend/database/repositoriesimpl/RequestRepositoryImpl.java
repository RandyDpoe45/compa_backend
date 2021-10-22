package com.wesdom.compa.backend.database.repositoriesimpl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.wesdom.compa.backend.database.jparepositories.RequestJpaRepository;
import com.wesdom.compa.backend.database.model.Product;
import com.wesdom.compa.backend.database.model.Request;
import com.wesdom.compa.backend.database.model.users.Association;
import com.wesdom.compa.backend.database.model.users.CommercialPartner;
import com.wesdom.compa.backend.database.repositories.IRequestRepository;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Service
public class RequestRepositoryImpl implements IRequestRepository {

    @Autowired
    private RequestJpaRepository requestJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Request get(Long id) {
        return requestJpaRepository.getOne(id);
    }

    @Override
    public Page<Request> getAll(Map<String, String> params) {
        IPredicateBuilder<Request> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return requestJpaRepository.findAll(predicate.createPredicate(params), paginaton.createPagination(params));
    }

    @Override
    public Request save(Request request) {
        request = requestJpaRepository.saveAndFlush(request);
        em.refresh(request);
        return request;
    }

    @Override
    public Request update(Long requestId, Request request) {
        Request request1 = requestJpaRepository.getOne(requestId);
        request1.setStatus(request.getStatus())
                .setPricePerUnit(request.getPricePerUnit())
                .setDeductedPricePerUnit(request.getDeductedPricePerUnit())
                .setAmount(request.getAmount())
                .setStatusHistory(request.getStatusHistory())
                .setStatusDateHistory(request.getStatusDateHistory())
                .setComments(request.getComments())
                .setMaxAmountOffer(request.getMaxAmountOffer())
                .setShippingDate(request.getShippingDate())
                .setOriginPlace(request.getOriginPlace())
                .setAmountShipped(request.getAmountShipped())
                .setTotalPayment(request.getTotalPayment());

        request = requestJpaRepository.saveAndFlush(request);
        em.refresh(request);
        return request;
    }

    @Override
    public void delete(Long requestId) {
        requestJpaRepository.deleteById(requestId);
    }

    @Override
    public Request findTop1ByProductId(Long id) {
        return requestJpaRepository.findTop1ByProductId(id);
    }
}
