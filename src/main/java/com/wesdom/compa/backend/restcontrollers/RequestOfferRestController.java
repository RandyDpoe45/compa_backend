package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.RequestOffer;
import com.wesdom.compa.backend.database.repositories.IRequestOfferRepository;
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
@RequestMapping("v1/requestOffer")
public class RequestOfferRestController {
    
    @Autowired
    private IRequestOfferRepository requestOfferRepository;


    @PostMapping
    @JsonView({SystemViews.RequestOfferBasicView.class})
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RequestOffer createUser(@RequestBody RequestOffer requestOffer){
        return requestOfferRepository.create(requestOffer);
    }

    @GetMapping
    @JsonView({SystemViews.RequestOfferBasicView.class})
    public Page<RequestOffer> getAll(@RequestParam Map<String,String> allParams){
        return requestOfferRepository.getAll(allParams);
    }

    @GetMapping("/{id}")
    @JsonView({SystemViews.RequestOfferBasicView.class})
    public RequestOffer get(@PathVariable Long id){
        return requestOfferRepository.get(id);
    }

    @PutMapping(value = "/{id}")
    @JsonView({SystemViews.RequestOfferBasicView.class})
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RequestOffer update(@PathVariable Long id, @RequestBody RequestOffer requestOffer) throws JsonProcessingException {
        return requestOfferRepository.update(id,requestOffer);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        requestOfferRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Oferta eliminada con exito");
    }
}
