/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.compa.backend.service.implementation;


import com.wesdom.compa.backend.database.model.MultimediaData;
import com.wesdom.compa.backend.database.repositories.IMultimediaDataRepository;
import com.wesdom.compa.backend.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.compa.backend.exceptionhandling.exceptions.GeneralException;
import com.wesdom.compa.backend.service.interfaces.IMultimediaDataService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author randy
 */
@Service
public class MultimediaDataServiceImpl implements IMultimediaDataService {

    @Autowired
    private IMultimediaDataRepository multimediaDataRepository;

    @Override
    public MultimediaData create(String imgType, Long entityId, String entityType, MultipartFile file) throws GeneralException {
        MultimediaData multimediaData = null;
        try {
            multimediaData = multimediaDataRepository.createMultimediaData(new MultimediaData().setEntityId(entityId).setEntityName(entityType));

            String fileName = multimediaData.getId() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            multimediaData.setFileName(fileName);
            multimediaData.setImgType(imgType);
            multimediaDataRepository.updateMultimediaData(multimediaData.getId(), multimediaData);

            Path p = Paths.get("multimedia/" + entityType);
            if (!Files.exists(p)) {
                Files.createDirectory(p);
            }
            Files.copy(file.getInputStream(), p.resolve(multimediaData.getFileName()));

        } catch (IOException ex) {
            Logger.getLogger(MultimediaDataServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new GeneralException(ExceptionCodesEnum.ERROR_UPLOADING_FILES, "Error cargando los archivos");
        }

        return multimediaData;
    }

    @Override
    public void delete(Long multimediaDataId) {
        MultimediaData multimediaData = null;
        try {
            multimediaData = multimediaDataRepository.getMultimediaData(multimediaDataId);
            Path p = Paths.get("multimedia/" + multimediaData.getEntityName());
            Path file = p.resolve(multimediaData.getFileName());
            Files.delete(file);
            multimediaDataRepository.deleteMultimediaData(multimediaDataId);

        } catch (IOException ex) {
            Logger.getLogger(MultimediaDataServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new GeneralException(ExceptionCodesEnum.ERROR_UPLOADING_FILES, "Error cargando los archivos");
        }
    }

    @Override
    public void delete(String entityType, String entityId) {
        Map<String, String> params = new HashMap<>();
        params.put("entityName", entityType);
        params.put("entityId", entityId);
        Page<MultimediaData> descriptors = multimediaDataRepository.getAll(params);
        try {
            for (MultimediaData desc : descriptors) {
                Path p = Paths.get("multimedia/" + desc.getEntityName());
                Path file = p.resolve(desc.getFileName());
                Files.delete(file);
                multimediaDataRepository.deleteMultimediaData(desc.getId());
            }
        } catch (IOException ex) {
            Logger.getLogger(MultimediaDataServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new GeneralException(ExceptionCodesEnum.ERROR_UPLOADING_FILES, "Error cargando los archivos");
        }

    }

    @Override
    public void deleteUrlData(List<Long> ids) {
        List<MultimediaData> multimediaDataList = multimediaDataRepository.findAllByIdAndEntityTypeAndImgType(ids,"Product" , "URL");
        multimediaDataRepository.deleteAll(multimediaDataList);
    }


    @Override
    public Resource getMultimedia(Long multimediaId) {
        Resource fileR = null;
        MultimediaData descriptor = multimediaDataRepository.getMultimediaData(multimediaId);
        try {
            Path root = Paths.get("multimedia/" + descriptor.getEntityName());
            if (Files.exists(root)) {
                Path file = root.resolve(descriptor.getFileName());
                Resource resource = new UrlResource(file.toUri());

                if (resource.exists() || resource.isReadable()) {
                    fileR = resource;
                }

            }

        } catch (MalformedURLException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
        return fileR;

    }

}
