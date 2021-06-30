/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.MultimediaData;
import com.wesdom.compa.backend.database.repositories.IMultimediaDataRepository;
import com.wesdom.compa.backend.dtos.GeneralResponse;
import com.wesdom.compa.backend.service.interfaces.IMultimediaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 *
 * @author randy
 */
@RestController
@RequestMapping("v1/multimediaData")
@CrossOrigin(origins = {"*"})
public class MultimediaDataRestController {
    
    @Autowired
    private IMultimediaDataRepository multimediaDataRepository;
    
    @Autowired
    private IMultimediaDataService multimediaDataService;
    
    @PostMapping
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GeneralResponse create(@RequestParam("entityId") Long entityId, @RequestParam("entityType") String entityType,
                                  @RequestParam("imgType") String imgType, @RequestParam("files") MultipartFile[] files)
            throws JsonProcessingException{
        
        for(MultipartFile file :  files){
            multimediaDataService.create(imgType, entityId, entityType, file);
        }
        
        return new GeneralResponse("Cargado con exito", "000");
    }
    
    @GetMapping("/{multimediaId}")
    public ResponseEntity<Resource> get(@PathVariable Long multimediaId){
        Resource file = multimediaDataService.getMultimedia(multimediaId);
        if(file != null){
            return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        }
        return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + null + "\"").body(null);
    }
    
    @GetMapping
    public List<MultimediaData> getMultimediaDescriptors(@RequestParam Map<String, String> params){
        return multimediaDataRepository.getAll(params).getContent();
    }
    
    @DeleteMapping("/{entityType}/{entityId}")
    public GeneralResponse delete(@PathVariable String entityType, @PathVariable String entityId){
        multimediaDataService.delete(entityType, entityId);
        return new GeneralResponse("Archivos borrados con exito", "000");
    }
    
    @DeleteMapping("/{multimediaId}")
    public GeneralResponse delete(@PathVariable Long multimediaId){
        multimediaDataService.delete(multimediaId);
        return new GeneralResponse("Archivo borrados con exito", "000");
    }
    
}
