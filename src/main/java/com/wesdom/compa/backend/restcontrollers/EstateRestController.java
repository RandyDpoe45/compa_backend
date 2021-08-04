package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.Estate;
import com.wesdom.compa.backend.database.repositories.IEstateRepository;
import com.wesdom.compa.backend.dtos.GeneralResponse;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("v1/estate")
public class EstateRestController {
    
    @Autowired
    private IEstateRepository estateRepository;

    @PostMapping
    @JsonView(SystemViews.EstateBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Estate createUser(@RequestBody Estate estate){
        return estateRepository.save(estate);
    }

    @JsonView(SystemViews.EstateBasicView.class)
    @GetMapping
    public Page<Estate> getAll(@RequestParam Map<String,String> allParams){
        return estateRepository.getAll(allParams);
    }

    @JsonView(SystemViews.EstateBasicView.class)
    @GetMapping("/{id}")
    public Estate get(@PathVariable Long id){
        return estateRepository.get(id);
    }

    @JsonView(SystemViews.EstateBasicView.class)
    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Estate update(@PathVariable Long id, @RequestBody Estate estate) throws JsonProcessingException {
        return estateRepository.update(id,estate);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        estateRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Predio eliminado con exito");
    }
}
