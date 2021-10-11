package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.activity.Activity;
import com.wesdom.compa.backend.database.model.activity.ActivityOption;
import com.wesdom.compa.backend.database.model.OptionAnswer;
import com.wesdom.compa.backend.database.repositories.IActivityRepository;
import com.wesdom.compa.backend.database.repositories.IActivityOptionRepository;
import com.wesdom.compa.backend.database.repositories.IOptionAnswerRepository;
import com.wesdom.compa.backend.dtos.GeneralResponse;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import com.wesdom.compa.backend.service.interfaces.IActivityService;
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

    @Autowired
    private IActivityService activityService;

    @GetMapping
    @JsonView(SystemViews.ActivityBasicView.class)
    public Page<Activity> getAll(@RequestParam Map<String,String> params){
        return activityRepository.getAll(params);
    }

    @GetMapping("/{id}")
    public Activity get(@PathVariable Long id){
        return activityRepository.get(id);
    }

    @PostMapping
    @JsonView(SystemViews.ActivityBasicView.class)
    public Activity save(@RequestBody Activity activity){
        return activityRepository.save(activity);
    }

    @PutMapping(value = "/{id}")
    @JsonView(SystemViews.ActivityBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Activity update(@PathVariable Long id, @RequestBody Activity activity) throws JsonProcessingException {
        return activityRepository.update(id,activity);
    }

    @DeleteMapping("/{id}")
    public GeneralResponse delete(@PathVariable Long id){
        activityService.deleteActivity(id);
        return new GeneralResponse("Actividad borrada con exito","000");
    }

    @GetMapping("/activityOption")
    @JsonView(SystemViews.ActivityOptionBasicView.class)
    public Page<ActivityOption> getAllActivityOption(@RequestParam Map<String,String> params){
        return activityOptionRepository.getAll(params);
    }

    @GetMapping("/activityOption/{id}")
    @JsonView(SystemViews.ActivityOptionBasicView.class)
    public ActivityOption getActivityOption(@PathVariable Long id){
        return activityOptionRepository.get(id);
    }

    @PostMapping("/activityOption")
    @JsonView(SystemViews.ActivityOptionBasicView.class)
    public ActivityOption createActivityOption(@RequestBody ActivityOption activityOption){
        return activityOptionRepository.save(activityOption);
    }

    @PutMapping(value = "/activityOption/{id}")
    @JsonView(SystemViews.ActivityOptionBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public ActivityOption updateActivityOption(@PathVariable Long id, @RequestBody ActivityOption activityOption) throws JsonProcessingException {
        return activityOptionRepository.update(id,activityOption);
    }

    @DeleteMapping("/activityOption/{id}")
    public GeneralResponse deleteActivityOption(@PathVariable Long id){
        activityOptionRepository.delete(id);
        return new GeneralResponse("Opcion borrada con exito","000");
    }

    @GetMapping("/activityOption/optionAnswer")
    @JsonView(SystemViews.OptionAnswerBasicView.class)
    public Page<OptionAnswer> getAllOptionAnswer(@RequestParam Map<String,String> params){
        return optionAnswerRepository.getAll(params);
    }

    @GetMapping("/activityOption/optionAnswer/{id}")
    @JsonView(SystemViews.OptionAnswerBasicView.class)
    public OptionAnswer getOptionAnswer(@PathVariable Long id){
        return optionAnswerRepository.get(id);
    }

    @PostMapping("/activityOption/optionAnswer")
    @JsonView(SystemViews.OptionAnswerBasicView.class)
    public OptionAnswer createOptionAnswer(@RequestBody OptionAnswer optionAnswer){
        return optionAnswerRepository.save(optionAnswer);
    }

    @PutMapping(value = "/activityOption/optionAnswer/{id}")
    @JsonView(SystemViews.OptionAnswerBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public OptionAnswer updateOptionAnswer(@PathVariable Long id, @RequestBody OptionAnswer optionAnswer) throws JsonProcessingException {
        return optionAnswerRepository.update(id,optionAnswer);
    }

    @DeleteMapping("/activityOption/optionAnswer/{id}")
    public GeneralResponse deleteOptionAnswer(@PathVariable Long id){
        optionAnswerRepository.delete(id);
        return new GeneralResponse("Respuesta borrada con exito","000");
    }
}
