/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.compa.backend.service.interfaces;

import com.wesdom.compa.backend.database.model.MultimediaData;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author randy
 */
public interface IMultimediaDataService {
    
    default void init() throws IOException{
        Path p = Paths.get("multimedia");
        if(!Files.exists(p)){
            Files.createDirectory(p);
        }
    }
    
    MultimediaData create(String imgType, Long entityId, String entityType, MultipartFile file);
    
    Resource getMultimedia(Long multimediaId);
       
    void delete(Long multimediaDataId);
    
    void delete(String entityType, String entityId);

    void deleteUrlData(List<Long> ids);
}
