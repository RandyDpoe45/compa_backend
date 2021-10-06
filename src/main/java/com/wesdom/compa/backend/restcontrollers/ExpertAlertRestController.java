package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.ExpertAlert;
import com.wesdom.compa.backend.database.repositories.IExpertAlertRepository;
import com.wesdom.compa.backend.dtos.GeneralResponse;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import com.wesdom.compa.backend.service.interfaces.IExpertAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("v1/expertAlert")
public class ExpertAlertRestController {

    @Autowired
    private IExpertAlertRepository expertAlertRepository;

    @Autowired
    private IExpertAlertService expertAlertService;

    @PostMapping
    @JsonView(SystemViews.ExpertAlertBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public ExpertAlert create(@RequestBody ExpertAlert expertAlert){
        return expertAlertService.create(expertAlert);
    }

    @GetMapping
    @JsonView(SystemViews.ExpertAlertBasicView.class)
    public Page<ExpertAlert> getAll(@RequestParam Map<String,String> allParams){
        return expertAlertRepository.getAll(allParams);
    }

    @GetMapping("/{id}")
    @JsonView(SystemViews.ExpertAlertBasicView.class)
    public ExpertAlert get(@PathVariable Long id){
        return expertAlertRepository.get(id);
    }

    @PutMapping(value = "/{id}")
    @JsonView(SystemViews.ExpertAlertBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public ExpertAlert update(@PathVariable Long id, @RequestBody ExpertAlert expertAlert) throws JsonProcessingException {
        return expertAlertRepository.update(id,expertAlert);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        expertAlertRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Alerta eliminada con exito");
    }

}
