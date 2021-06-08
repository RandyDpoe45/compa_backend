package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.MeasureUnit;
import com.wesdom.compa.backend.database.repositories.IFloraRepository;
import com.wesdom.compa.backend.database.repositories.IMeasureUnitRepository;
import com.wesdom.compa.backend.dtos.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("v1/measureUnit")
public class MeasureUnitRestController {
    
    @Autowired
    private IMeasureUnitRepository measureUnitRepository;


    @PostMapping
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MeasureUnit create(@RequestBody MeasureUnit measureUnit){
        return measureUnitRepository.create(measureUnit);
    }

    @GetMapping
    public Page<MeasureUnit> getAll(@RequestParam Map<String,String> allParams){
        return measureUnitRepository.getAll(allParams);
    }

    @GetMapping("/{id}")
    public MeasureUnit get(@PathVariable Long id){
        return measureUnitRepository.get(id);
    }

    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MeasureUnit update(@PathVariable Long id, @RequestBody MeasureUnit measureUnit) throws JsonProcessingException {
        return measureUnitRepository.update(id,measureUnit);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        measureUnitRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Unidad de medida eliminada con exito");
    }
}
