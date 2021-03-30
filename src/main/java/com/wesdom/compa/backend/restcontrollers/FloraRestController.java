package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.Flora;
import com.wesdom.compa.backend.database.repositories.IFloraRepository;
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
@RequestMapping("v1/flora")
public class FloraRestController {
    
    @Autowired
    private IFloraRepository floraRepository;


    @PostMapping
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Flora createUser(@RequestBody Flora flora){
        return floraRepository.create(flora);
    }

    @GetMapping
    public Page<Flora> getAll(@RequestParam Map<String,String> allParams){
        return floraRepository.getAll(allParams);
    }

    @GetMapping("/{id}")
    public Flora get(@PathVariable Long id){
        return floraRepository.get(id);
    }

    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Flora update(@PathVariable Long id, @RequestBody Flora flora) throws JsonProcessingException {
        return floraRepository.update(id,flora);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        floraRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Predio eliminado con exito");
    }
}
