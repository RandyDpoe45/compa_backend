package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.Activity;
import com.wesdom.compa.backend.database.model.ActivityOption;
import com.wesdom.compa.backend.database.model.OptionAnswer;
import com.wesdom.compa.backend.database.repositories.IActivityRepository;
import com.wesdom.compa.backend.database.repositories.IActivityOptionRepository;
import com.wesdom.compa.backend.database.repositories.IOptionAnswerRepository;
import com.wesdom.compa.backend.dtos.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("v1/activity")
public class ActivityRestController {
    
    @Autowired
    private IActivityRepository activityRepository;

    @Autowired
    private IActivityOptionRepository activityOptionRepository;

    @Autowired
    private IOptionAnswerRepository optionAnswerRepository;

    @GetMapping
    public Page<Activity> getAll(@RequestParam Map<String,String> params){
        return activityRepository.getAll(params);
    }

    @GetMapping("/{id}")
    public Activity get(@PathVariable Long id){
        return activityRepository.get(id);
    }

    @PostMapping
    public Activity create(@RequestBody Activity activity){
        return activityRepository.create(activity);
    }

    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Activity update(@PathVariable Long id, @RequestBody Activity activity) throws JsonProcessingException {
        return activityRepository.update(id,activity);
    }

    @DeleteMapping("/{id}")
    public GeneralResponse delete(@PathVariable Long id){
        activityRepository.delete(id);
        return new GeneralResponse("Actividad borrada con exito","000");
    }

    @GetMapping("/activityOption")
    public Page<ActivityOption> getAllActivityOption(@RequestParam Map<String,String> params){
        return activityOptionRepository.getAll(params);
    }

    @GetMapping("/activityOption/{id}")
    public ActivityOption getActivityOption(@PathVariable Long id){
        return activityOptionRepository.get(id);
    }

    @PostMapping("/activityOption")
    public ActivityOption createActivityOption(@RequestBody ActivityOption activityOption){
        return activityOptionRepository.create(activityOption);
    }

    @PutMapping(value = "/activityOption/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ActivityOption updateActivityOption(@PathVariable Long id, @RequestBody ActivityOption activityOption) throws JsonProcessingException {
        return activityOptionRepository.update(id,activityOption);
    }

    @DeleteMapping("/activityOption/{id}")
    public GeneralResponse deleteActivityOption(@PathVariable Long id){
        activityOptionRepository.delete(id);
        return new GeneralResponse("Opcion borrada con exito","000");
    }

    @GetMapping("/activityOption/optionAnswer")
    public Page<OptionAnswer> getAllOptionAnswer(@RequestParam Map<String,String> params){
        return optionAnswerRepository.getAll(params);
    }

    @GetMapping("/activityOption/optionAnswer/{id}")
    public OptionAnswer getOptionAnswer(@PathVariable Long id){
        return optionAnswerRepository.get(id);
    }

    @PostMapping("/activityOption/optionAnswer")
    public OptionAnswer createOptionAnswer(@RequestBody OptionAnswer optionAnswer){
        return optionAnswerRepository.create(optionAnswer);
    }

    @PutMapping(value = "/activityOption/optionAnswer/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OptionAnswer updateOptionAnswer(@PathVariable Long id, @RequestBody OptionAnswer optionAnswer) throws JsonProcessingException {
        return optionAnswerRepository.update(id,optionAnswer);
    }

    @DeleteMapping("/activityOption/optionAnswer/{id}")
    public GeneralResponse deleteOptionAnswer(@PathVariable Long id){
        optionAnswerRepository.delete(id);
        return new GeneralResponse("Respuesta borrada con exito","000");
    }
}
