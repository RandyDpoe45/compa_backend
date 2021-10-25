package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.Request;
import com.wesdom.compa.backend.database.repositories.IRequestRepository;
import com.wesdom.compa.backend.dtos.GeneralResponse;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import com.wesdom.compa.backend.service.interfaces.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("v1/request")
public class RequestRestController {
    
    @Autowired
    private IRequestRepository requestRepository;

    @Autowired
    private IRequestService requestService;


    @PostMapping
    @JsonView({SystemViews.RequestDetailView.class})
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Request create(@RequestBody Request request){
        return requestService.createRequest(request);
    }

    @GetMapping
    @JsonView({SystemViews.RequestBasicView.class})
    public Page<Request> getAll(@RequestParam Map<String,String> allParams){
        return requestRepository.getAll(allParams);
    }

    @GetMapping("/{id}")
    @JsonView({SystemViews.RequestDetailView.class})
    public Request get(@PathVariable Long id){
        return requestRepository.get(id);
    }

    @PutMapping(value = "/{id}")
    @JsonView({SystemViews.RequestDetailView.class})
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Request update(@PathVariable Long id, @RequestBody Request request) throws JsonProcessingException {
        return requestService.updateRequest(id,request);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        requestRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Solicitud eliminada con exito");
    }
}
