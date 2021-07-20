package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.BioProduct;
import com.wesdom.compa.backend.database.repositories.IBioProductRepository;
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
@RequestMapping("v1/bioProduct")
public class BioProductRestController {

    @Autowired
    private IBioProductRepository bioProductRepository;

    @GetMapping
    @JsonView(SystemViews.BioProductBasicView.class)
    public Page<BioProduct> getAll(@RequestParam Map<String,String> params){
        return bioProductRepository.getAll(params);
    }

    @GetMapping("/{id}")
    @JsonView(SystemViews.BioProductBasicView.class)
    public BioProduct get(@PathVariable Long id){
        return bioProductRepository.get(id);
    }

    @PostMapping
    @JsonView(SystemViews.BioProductBasicView.class)
    public BioProduct create(@RequestBody BioProduct bioProduct){
        return bioProductRepository.create(bioProduct);
    }

    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @JsonView(SystemViews.BioProductBasicView.class)
    public BioProduct update(@PathVariable Long id, @RequestBody BioProduct bioProduct) throws JsonProcessingException {
        return bioProductRepository.update(id,bioProduct);
    }

    @DeleteMapping("/{id}")
    public GeneralResponse delete(@PathVariable Long id){
        bioProductRepository.delete(id);
        return new GeneralResponse("BioProducto borrado con exito","000");
    }
}
