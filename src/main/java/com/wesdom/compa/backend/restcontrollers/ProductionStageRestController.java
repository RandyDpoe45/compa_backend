package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.ProductionStage;
import com.wesdom.compa.backend.database.repositories.IProductionStageRepository;
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
@RequestMapping("v1/productionStage")
public class ProductionStageRestController {
    
    @Autowired
    private IProductionStageRepository productionStageRepository;


    @PostMapping
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @JsonView(SystemViews.ProductionStageBasicView.class)
    public ProductionStage createUser(@RequestBody ProductionStage productionStage){
        return productionStageRepository.create(productionStage);
    }

    @GetMapping
    @JsonView(SystemViews.ProductionStageBasicView.class)
    public Page<ProductionStage> getAll(@RequestParam Map<String,String> allParams){
        return productionStageRepository.getAll(allParams);
    }

    @GetMapping("/{id}")
    @JsonView(SystemViews.ProductionStageBasicView.class)
    public ProductionStage get(@PathVariable Long id){
        return productionStageRepository.get(id);
    }

    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @JsonView(SystemViews.ProductionStageBasicView.class)
    public ProductionStage update(@PathVariable Long id, @RequestBody ProductionStage productionStage) throws JsonProcessingException {
        return productionStageRepository.update(id,productionStage);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        productionStageRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Fase de produccion eliminada con exito");
    }
}
