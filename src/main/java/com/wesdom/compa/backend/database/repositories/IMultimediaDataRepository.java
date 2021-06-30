/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.compa.backend.database.repositories;


import com.wesdom.compa.backend.database.model.MultimediaData;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;

/**
 *
 * @author randy
 */
public interface IMultimediaDataRepository {
    
    
    MultimediaData createMultimediaData(MultimediaData multimediaData);
    
    MultimediaData updateMultimediaData(Long multimediaDataId, MultimediaData multimediaData);
    
    void deleteMultimediaData(Long multimediaDataId);
    
    MultimediaData getMultimediaData(Long multimediaDataId);
    
    Page<MultimediaData> getAll(@Nullable Map<String,String> params);

    List<MultimediaData> findAllByIdAndEntityTypeAndImgType(List<Long> ids, String entityType, String imgType);

    void deleteAll(List<MultimediaData> datas);

    List<MultimediaData> saveAll(List<MultimediaData> multimediaDataList);
    
}
