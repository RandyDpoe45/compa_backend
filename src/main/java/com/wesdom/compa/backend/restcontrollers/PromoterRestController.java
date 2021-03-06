package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.Promoter;
import com.wesdom.compa.backend.database.repositories.IPromoterRepository;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("v1/promoter")
public class PromoterRestController {
    
    @Autowired
    private IPromoterRepository promoterRepository;

    @PostMapping
    @JsonView(SystemViews.PromoterBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Promoter createUser(@RequestBody Promoter promoter){
        return promoterRepository.create(promoter);
    }

    @JsonView(SystemViews.PromoterBasicView.class)
    @GetMapping
    public Page<Promoter> getAll(@RequestParam Map<String,String> allParams){
        return promoterRepository.getAll(allParams);
    }

    @JsonView(SystemViews.PromoterBasicView.class)
    @GetMapping("/{id}")
    public Promoter get(@PathVariable Long id){
        return promoterRepository.get(id);
    }

    @JsonView(SystemViews.PromoterBasicView.class)
    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Promoter update(@PathVariable Long id, @RequestBody Promoter promoter) throws JsonProcessingException {
        return promoterRepository.update(id,promoter);
    }
    
}
