package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.users.CommercialPartner;
import com.wesdom.compa.backend.database.repositories.ICommercialPartnerRepository;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("v1/commercialPartner")
public class CommercialPartnerRestController {

    @Autowired
    private ICommercialPartnerRepository commercialPartnerRepository;

    @PostMapping
    @JsonView(SystemViews.CommercialPartnerBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CommercialPartner createUser(@RequestBody CommercialPartner commercialPartner){
        return commercialPartnerRepository.save(commercialPartner);
    }

    @JsonView(SystemViews.CommercialPartnerBasicView.class)
    @GetMapping
    public Page<CommercialPartner> getAll(@RequestParam Map<String,String> allParams){
        return commercialPartnerRepository.getAll(allParams);
    }

    @JsonView(SystemViews.CommercialPartnerBasicView.class)
    @GetMapping("/{id}")
    public CommercialPartner get(@PathVariable Long id){
        return commercialPartnerRepository.get(id);
    }

    @JsonView(SystemViews.CommercialPartnerBasicView.class)
    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CommercialPartner update(@PathVariable Long id, @RequestBody CommercialPartner commercialPartner) throws JsonProcessingException {
        return commercialPartnerRepository.update(id,commercialPartner);
    }
}
