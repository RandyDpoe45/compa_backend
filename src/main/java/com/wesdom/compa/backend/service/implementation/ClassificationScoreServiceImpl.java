package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.model.ClassificationScoreDescription;
import com.wesdom.compa.backend.database.repositories.IClassificationScoreDescriptionRepository;
import com.wesdom.compa.backend.database.repositories.IClassificationScoreRepository;
import com.wesdom.compa.backend.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.compa.backend.exceptionhandling.exceptions.GeneralException;
import com.wesdom.compa.backend.service.interfaces.IClassificationScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassificationScoreServiceImpl implements IClassificationScoreService {

    @Autowired
    private IClassificationScoreRepository classificationScoreRepository;

    @Autowired
    private IClassificationScoreDescriptionRepository classificationScoreDescriptionRepository;

    @Override
    public void delete(Long id) {
        ClassificationScoreDescription classificationScoreDescription =
                classificationScoreDescriptionRepository.findTop1ByClassificationScoreId(id);
        if(classificationScoreDescription != null){
            throw new GeneralException(
                    ExceptionCodesEnum.CLASSIFICATION_IN_USE,
                    "Clasificacion en uso."
            );

        }else{
            classificationScoreRepository.delete(id);
        }
    }
}
