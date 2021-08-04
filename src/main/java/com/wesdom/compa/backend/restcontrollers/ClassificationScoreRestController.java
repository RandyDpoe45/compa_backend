package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.ClassificationScore;
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
@RequestMapping("v1/classificationScore")
public class ClassificationScoreRestController {
    
    @Autowired
    private IClassificationScoreRepository classificationScoreRepository;

    @PostMapping
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ClassificationScore create(@RequestBody ClassificationScore classificationScore){
        return classificationScoreRepository.save(classificationScore);
    }

    @GetMapping("/score/{score}")
    public ClassificationScore getScore(@PathVariable Float score){
        return classificationScoreRepository.getClassificationScoreBetweenRange(score);
    }

    @GetMapping
    public Page<ClassificationScore> getAll(@RequestParam Map<String,String> allParams){
        return classificationScoreRepository.getAll(allParams);
    }

    @GetMapping("/{id}")
    public ClassificationScore get(@PathVariable Long id){
        return classificationScoreRepository.get(id);
    }

    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ClassificationScore update(@PathVariable Long id, @RequestBody ClassificationScore classificationScore) throws JsonProcessingException {
        return classificationScoreRepository.update(id,classificationScore);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        classificationScoreRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Clasificacion eliminada con exito");
    }
}
