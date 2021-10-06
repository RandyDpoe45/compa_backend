package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.PackingOption;
import com.wesdom.compa.backend.database.repositories.IPackingOptionRepository;
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
@RequestMapping("v1/product/packingOption")
public class PackingOptionRestController {

    @Autowired
    private IPackingOptionRepository packingOptionRepository;


    @PostMapping
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @JsonView(SystemViews.PackingOptionBasicView.class)
    public PackingOption createUser(@RequestBody PackingOption packingOption) {
        return packingOptionRepository.save(packingOption);
    }

    @GetMapping
    @JsonView(SystemViews.PackingOptionBasicView.class)
    public Page<PackingOption> getAll(@RequestParam Map<String, String> allParams) {
        return packingOptionRepository.getAll(allParams);
    }

    @GetMapping("/{id}")
    @JsonView(SystemViews.PackingOptionBasicView.class)
    public PackingOption get(@PathVariable Long id) {
        return packingOptionRepository.get(id);
    }

    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @JsonView(SystemViews.PackingOptionBasicView.class)
    public PackingOption update(@PathVariable Long id, @RequestBody PackingOption packingOption) throws JsonProcessingException {
        return packingOptionRepository.update(id, packingOption);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        packingOptionRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Opcion de empaque eliminada con exito");
    }
}
