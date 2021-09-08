package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.PostHarvestCheck;
import com.wesdom.compa.backend.database.repositories.IPostHarvestCheckRepository;
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
@RequestMapping("v1/product/postHarvestCheck")
public class PostHarvestCheckRestController {

    @Autowired
    private IPostHarvestCheckRepository postHarvestCheckRepository;


    @PostMapping
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @JsonView(SystemViews.PostHarvestCheckBasicView.class)
    public PostHarvestCheck createUser(@RequestBody PostHarvestCheck postHarvestCheck){
        return postHarvestCheckRepository.save(postHarvestCheck);
    }

    @GetMapping
    @JsonView(SystemViews.PostHarvestCheckBasicView.class)
    public Page<PostHarvestCheck> getAll(@RequestParam Map<String,String> allParams){
        return postHarvestCheckRepository.getAll(allParams);
    }

    @GetMapping("/{id}")
    @JsonView(SystemViews.PostHarvestCheckBasicView.class)
    public PostHarvestCheck get(@PathVariable Long id){
        return postHarvestCheckRepository.get(id);
    }

    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @JsonView(SystemViews.PostHarvestCheckBasicView.class)
    public PostHarvestCheck update(@PathVariable Long id, @RequestBody PostHarvestCheck postHarvestCheck) throws JsonProcessingException {
        return postHarvestCheckRepository.update(id,postHarvestCheck);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        postHarvestCheckRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Validacion eliminada con exito");
    }
}
