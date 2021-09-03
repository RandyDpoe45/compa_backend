package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.activity.ProductionActivity;
import com.wesdom.compa.backend.database.model.activity.ProductionActivityAnswer;
import com.wesdom.compa.backend.database.repositories.IProductionActivityAnswerRepository;
import com.wesdom.compa.backend.database.repositories.IProductionActivityRepository;
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
@RequestMapping("v1/productionActivity")
public class ProductionActivityRestController {
    
    @Autowired
    private IProductionActivityAnswerRepository productionActivityAnswerRepository;

    @Autowired
    private IProductionActivityRepository productionActivityRepository;

    //cambiar creacion a dto
    @PostMapping
    @JsonView(SystemViews.ProductionActivityBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ProductionActivity save(@RequestBody ProductionActivity productionActivity){
        return productionActivityRepository.save(productionActivity);
    }

    @GetMapping
    @JsonView(SystemViews.ProductionActivityBasicView.class)
    public Page<ProductionActivity> getAll(@RequestParam Map<String,String> allParams){
        return productionActivityRepository.getAll(allParams);
    }

    @GetMapping("/{id}")
    @JsonView(SystemViews.ProductionActivityBasicView.class)
    public ProductionActivity get(@PathVariable Long id){
        return productionActivityRepository.get(id);
    }

    @PutMapping(value = "/{id}")
    @JsonView(SystemViews.ProductionActivityBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ProductionActivity update(@PathVariable Long id, @RequestBody ProductionActivity productionActivity) throws JsonProcessingException {
        return productionActivityRepository.update(id,productionActivity);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        productionActivityRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Producto en predio eliminado con exito");
    }

    @PostMapping("/productionActivityAnswer")
    @JsonView(SystemViews.ProductionActivityAnswerBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ProductionActivityAnswer createAnswer(@RequestBody ProductionActivityAnswer productionActivityAnswer){
        return productionActivityAnswerRepository.save(productionActivityAnswer);
    }

    @GetMapping("/productionActivityAnswer")
    @JsonView(SystemViews.ProductionActivityAnswerBasicView.class)
    public Page<ProductionActivityAnswer> getAllAnswers(@RequestParam Map<String,String> allParams){
        return productionActivityAnswerRepository.getAll(allParams);
    }

    @GetMapping("/productionActivityAnswer/{id}")
    @JsonView(SystemViews.ProductionActivityAnswerBasicView.class)
    public ProductionActivityAnswer getAnswer(@PathVariable Long id){
        return productionActivityAnswerRepository.get(id);
    }

    @PutMapping(value = "/productionActivityAnswer/{id}")
    @JsonView(SystemViews.ProductionActivityAnswerBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ProductionActivityAnswer updateAnswer(@PathVariable Long id, @RequestBody ProductionActivityAnswer productionActivityAnswer) throws JsonProcessingException {
        return productionActivityAnswerRepository.update(id,productionActivityAnswer);
    }

    @DeleteMapping(value = "/productionActivityAnswer/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GeneralResponse deleteAnswer(@PathVariable Long id) throws JsonProcessingException {
        productionActivityAnswerRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Producto en predio eliminado con exito");
    }
}
