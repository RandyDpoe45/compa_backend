package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.estatesegment.ProductWork;
import com.wesdom.compa.backend.database.repositories.IProductWorkRepository;
import com.wesdom.compa.backend.dtos.GeneralResponse;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import com.wesdom.compa.backend.service.interfaces.IProductInSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("v1/productWork")
public class ProductWorkRestController {

    @Autowired
    private IProductWorkRepository productWorkRepository;


    @PostMapping
    @JsonView(SystemViews.ProductWorkBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ProductWork save(@RequestBody ProductWork productWork){
        return productWorkRepository.save(productWork);
    }

    @GetMapping
    @JsonView(SystemViews.ProductWorkBasicView.class)
    public Page<ProductWork> getAll(@RequestParam Map<String,String> allParams){
        return productWorkRepository.getAll(allParams);
    }

    @GetMapping("/{id}")
    @JsonView(SystemViews.ProductWorkBasicView.class)
    public ProductWork get(@PathVariable Long id){
        return productWorkRepository.get(id);
    }

    @PutMapping(value = "/{id}")
    @JsonView(SystemViews.ProductWorkBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ProductWork update(@PathVariable Long id, @RequestBody ProductWork productWork) throws JsonProcessingException {
        return productWorkRepository.update(id,productWork);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        productWorkRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Jornal eliminado con exito");
    }
}
