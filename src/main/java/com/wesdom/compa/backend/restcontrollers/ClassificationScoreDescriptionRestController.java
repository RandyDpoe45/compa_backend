package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.ClassificationScoreDescription;
import com.wesdom.compa.backend.database.repositories.IClassificationScoreDescriptionRepository;
import com.wesdom.compa.backend.database.repositories.IClassificationScoreRepository;
import com.wesdom.compa.backend.dtos.GeneralResponse;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("v1/classificationScoreDescription")
public class ClassificationScoreDescriptionRestController {
    
    @Autowired
    private IClassificationScoreDescriptionRepository classificationScoreDescriptionRepository;

    @PostMapping
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @JsonView(SystemViews.ClassificationScoreDescriptionBasicView.class)
    public ClassificationScoreDescription create(@RequestBody ClassificationScoreDescription classificationScore){
        return classificationScoreDescriptionRepository.save(classificationScore);
    }

    @GetMapping("/score/{score}/{entityName}")
    @JsonView(SystemViews.ClassificationScoreDescriptionBasicView.class)
    public ClassificationScoreDescription getScore(@PathVariable Float score, @PathVariable String entityName){
        return classificationScoreDescriptionRepository.getClassificationScoreBetweenRangeAndEntityName(score, entityName);
    }

    @GetMapping
    @JsonView(SystemViews.ClassificationScoreDescriptionBasicView.class)
    public Page<ClassificationScoreDescription> getAll(@RequestParam Map<String,String> allParams){
        return classificationScoreDescriptionRepository.getAll(allParams);
    }

    @GetMapping("/{id}")
    @JsonView(SystemViews.ClassificationScoreDescriptionBasicView.class)
    public ClassificationScoreDescription get(@PathVariable Long id){
        return classificationScoreDescriptionRepository.get(id);
    }

    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @JsonView(SystemViews.ClassificationScoreDescriptionBasicView.class)
    public ClassificationScoreDescription update(
            @PathVariable Long id,
            @RequestBody ClassificationScoreDescription classificationScore
    ) throws JsonProcessingException {
        return classificationScoreDescriptionRepository.update(id,classificationScore);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        classificationScoreDescriptionRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Descripcion de clasificacion eliminada con exito");
    }
}
