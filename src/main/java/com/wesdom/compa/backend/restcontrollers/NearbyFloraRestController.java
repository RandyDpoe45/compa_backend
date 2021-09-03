package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.estatesegment.NearbyFlora;
import com.wesdom.compa.backend.database.repositories.INearbyFloraRepository;
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
@RequestMapping("v1/nearbyFlora")
public class NearbyFloraRestController {
    
    @Autowired
    private INearbyFloraRepository nearbyFloraRepository;


    @PostMapping
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @JsonView(SystemViews.NearbyFloraBasicView.class)
    public NearbyFlora createUser(@RequestBody NearbyFlora NearbyFlora){
        return nearbyFloraRepository.save(NearbyFlora);
    }

    @GetMapping
    @JsonView(SystemViews.NearbyFloraBasicView.class)
    public Page<NearbyFlora> getAll(@RequestParam Map<String,String> allParams){
        return nearbyFloraRepository.getAll(allParams);
    }

    @GetMapping("/{id}")
    @JsonView(SystemViews.NearbyFloraBasicView.class)
    public NearbyFlora get(@PathVariable Long id){
        return nearbyFloraRepository.get(id);
    }

    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @JsonView(SystemViews.NearbyFloraBasicView.class)
    public NearbyFlora update(@PathVariable Long id, @RequestBody NearbyFlora NearbyFlora) throws JsonProcessingException {
        return nearbyFloraRepository.update(id,NearbyFlora);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        nearbyFloraRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Flora cerca eliminada con exito");
    }
}
