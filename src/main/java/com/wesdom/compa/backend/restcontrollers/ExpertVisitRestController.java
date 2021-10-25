package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.ExpertVisit;
import com.wesdom.compa.backend.database.model.ExpertVisitType;
import com.wesdom.compa.backend.database.repositories.IExpertVisitRepository;
import com.wesdom.compa.backend.dtos.GeneralResponse;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import com.wesdom.compa.backend.service.interfaces.IExpertVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("v1/expertVisit")
public class ExpertVisitRestController {

    @Autowired
    private IExpertVisitRepository expertVisitRepository;

    @Autowired
    private IExpertVisitService expertVisitService;


    @PostMapping
    @JsonView(SystemViews.ExpertVisitBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public ExpertVisit createUser(@RequestBody ExpertVisit expertVisit){
        return expertVisitRepository.save(expertVisit);
    }

    @GetMapping
    @JsonView(SystemViews.ExpertVisitBasicView.class)
    public Page<ExpertVisit> getAll(@RequestParam Map<String,String> allParams){
        return expertVisitRepository.getAll(allParams);
    }

    @GetMapping("/types")
    public List<ExpertVisitType> getTypes(){
        return expertVisitRepository.getTypes();
    }

    @GetMapping("/{id}")
    @JsonView(SystemViews.ExpertVisitBasicView.class)
    public ExpertVisit get(@PathVariable Long id){
        return expertVisitRepository.get(id);
    }

    @PutMapping(value = "/{id}")
    @JsonView(SystemViews.ExpertVisitBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public ExpertVisit update(@PathVariable Long id, @RequestBody ExpertVisit expertVisit) throws JsonProcessingException {
        return expertVisitService.update(id,expertVisit);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        expertVisitRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Predio eliminado con exito");
    }

}
