/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.compa.backend.database.repositoriesimpl;


import com.wesdom.compa.backend.database.jparepositories.MultimediaDataJpaRepository;
import com.wesdom.compa.backend.database.model.MultimediaData;
import com.wesdom.compa.backend.database.repositories.IMultimediaDataRepository;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author randy
 */
@Service
public class MultimediaDataRepositoryImpl implements IMultimediaDataRepository {

    @Autowired
    private MultimediaDataJpaRepository multimediaDataJpaRepository;
    
    @Override
    public MultimediaData createMultimediaData(MultimediaData multimediaData) {
        return multimediaDataJpaRepository.save(multimediaData);
    }

    @Override
    public MultimediaData updateMultimediaData(Long multimediaDataId, MultimediaData multimediaData) {
        MultimediaData m = multimediaDataJpaRepository.getOne(multimediaDataId);
        m.setEntityId(multimediaData.getEntityId()).setEntityName(multimediaData.getEntityName()).
                setFileName(multimediaData.getFileName());
        return multimediaDataJpaRepository.save(m);
    }

    @Override
    public void deleteMultimediaData(Long multimediaDataId) {
        multimediaDataJpaRepository.deleteById(multimediaDataId);
    }

    @Override
    public MultimediaData getMultimediaData(Long multimediaDataId) {
        return multimediaDataJpaRepository.getOne(multimediaDataId);
    }

    @Override
    public Page<MultimediaData> getAll(Map<String, String> params) {
        IPaginationBuilder paginationBuilder = new PaginationBuilderImpl();
        IPredicateBuilder<MultimediaData> predicateBuilder = new PredicateBuilderServiceImpl<>();
        if(params == null){
            params = new HashMap<>();
        }
        Pageable p= paginationBuilder.createPagination(params);
        Specification<MultimediaData> pre = predicateBuilder.createPredicate(params);
        return multimediaDataJpaRepository.findAll(pre,p);
    }

    @Override
    public List<MultimediaData> findAllByIdAndEntityTypeAndImgType(List<Long> ids, String entityType, String imgType) {
        return multimediaDataJpaRepository.findAllById(ids);
    }

    @Override
    public void deleteAll(List<MultimediaData> datas) {
        multimediaDataJpaRepository.deleteAll(datas);
    }

    @Override
    public List<MultimediaData> saveAll(List<MultimediaData> multimediaDataList) {
        return multimediaDataJpaRepository.saveAll(multimediaDataList);
    }

}
