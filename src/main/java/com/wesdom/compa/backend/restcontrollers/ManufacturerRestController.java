package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.users.Manufacturer;
import com.wesdom.compa.backend.database.model.ManufacturerType;
import com.wesdom.compa.backend.database.repositories.IManufacturerRepository;
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
@RequestMapping("v1/manufacturer")
public class ManufacturerRestController {
    
    @Autowired
    private IManufacturerRepository manufacturerRepository;

    @PostMapping
    @JsonView(SystemViews.ManufacturerBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Manufacturer createUser(@RequestBody Manufacturer manufacturer){
        return manufacturerRepository.save(manufacturer);
    }

    @JsonView(SystemViews.ManufacturerBasicView.class)
    @GetMapping
    public Page<Manufacturer> getAll(@RequestParam Map<String,String> allParams){
        return manufacturerRepository.getAll(allParams);
    }

    @JsonView(SystemViews.ManufacturerBasicView.class)
    @GetMapping("/{id}")
    public Manufacturer get(@PathVariable Long id){
        return manufacturerRepository.get(id);
    }

    @GetMapping("/types")
    public List<ManufacturerType> getTypes(){
        return manufacturerRepository.getManufacturerTypeList();
    }

    @JsonView(SystemViews.ManufacturerBasicView.class)
    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Manufacturer update(@PathVariable Long id, @RequestBody Manufacturer manufacturer) throws JsonProcessingException {
        return manufacturerRepository.update(id,manufacturer);
    }
}
