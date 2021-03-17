package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.ProductInStateSegment;
import com.wesdom.compa.backend.database.repositories.IProductInStateSegmentRepository;
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
@RequestMapping("v1/productInStateSegment")
public class ProductInStateSegmentRestController {

    @Autowired
    private IProductInStateSegmentRepository productInStateSegmentRepository;

    @PostMapping
    @JsonView(SystemViews.ProductInStateBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ProductInStateSegment create(@RequestBody ProductInStateSegment productInStateSegment){
        return productInStateSegmentRepository.create(productInStateSegment);
    }

    @GetMapping
    @JsonView(SystemViews.ProductInStateBasicView.class)
    public Page<ProductInStateSegment> getAll(@RequestParam Map<String,String> allParams){
        return productInStateSegmentRepository.getAll(allParams);
    }

    @GetMapping("/{id}")
    @JsonView(SystemViews.ProductInStateBasicView.class)
    public ProductInStateSegment get(@PathVariable Long id){
        return productInStateSegmentRepository.get(id);
    }

    @PutMapping(value = "/{id}")
    @JsonView(SystemViews.ProductInStateBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ProductInStateSegment update(@PathVariable Long id, @RequestBody ProductInStateSegment productInStateSegment) throws JsonProcessingException {
        return productInStateSegmentRepository.update(id,productInStateSegment);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        productInStateSegmentRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Producto en predio eliminado con exito");
    }
}
