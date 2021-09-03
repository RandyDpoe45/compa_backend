package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.estatesegment.ApiaryWoodType;
import com.wesdom.compa.backend.database.repositories.IApiaryWoodTypeRepository;
import com.wesdom.compa.backend.dtos.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("v1/woodType")
public class WoodTypeRestController {

    @Autowired
    private IApiaryWoodTypeRepository apiaryWoodTypeRepository;


    @PostMapping
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ApiaryWoodType createUser(@RequestBody ApiaryWoodType flora){
        return apiaryWoodTypeRepository.save(flora);
    }

    @GetMapping
    public Page<ApiaryWoodType> getAll(@RequestParam Map<String,String> allParams){
        return apiaryWoodTypeRepository.getAll(allParams);
    }

    @GetMapping("/{id}")
    public ApiaryWoodType get(@PathVariable Long id){
        return apiaryWoodTypeRepository.get(id);
    }

    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ApiaryWoodType update(@PathVariable Long id, @RequestBody ApiaryWoodType flora) throws JsonProcessingException {
        return apiaryWoodTypeRepository.update(id,flora);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        apiaryWoodTypeRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Tipo de madera eliminado con exito");
    }
}
