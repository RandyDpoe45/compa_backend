package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.Advertising;
import com.wesdom.compa.backend.database.repositories.IAdvertisingRepository;
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
@RequestMapping("v1/advertising")
public class AdvertisingRestController {

    @Autowired
    private IAdvertisingRepository advertisingRepository;

    @PostMapping
    @JsonView(SystemViews.AdvertisingDetailView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Advertising save(@RequestBody Advertising advertising){
        return advertisingRepository.save(advertising);
    }

    @GetMapping
    @JsonView(SystemViews.AdvertisingBasicView.class)
    public Page<Advertising> getAll(@RequestParam Map<String,String> params){
        return advertisingRepository.getAll(params);
    }

    @GetMapping("/{id}")
    @JsonView(SystemViews.AdvertisingDetailView.class)
    public Advertising get(@PathVariable Long id){
        return advertisingRepository.get(id);
    }

    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @JsonView(SystemViews.AdvertisingDetailView.class)
    public Advertising update(@PathVariable Long id, @RequestBody Advertising advertising) throws JsonProcessingException {
        return advertisingRepository.update(id,advertising);
    }

    @DeleteMapping("/{id}")
    public GeneralResponse delete(@PathVariable Long id){
        advertisingRepository.delete(id);
        return new GeneralResponse("Publicidad borrada con exito","000");
    }
}
