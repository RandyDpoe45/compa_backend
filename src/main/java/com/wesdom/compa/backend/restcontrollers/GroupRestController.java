package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.ManufacturerGroup;
import com.wesdom.compa.backend.database.model.GroupType;
import com.wesdom.compa.backend.database.repositories.IGroupRepository;
import com.wesdom.compa.backend.dtos.GeneralResponse;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("v1/group")
public class GroupRestController {

    @Autowired
    private IGroupRepository groupRepository;

    @PostMapping
    @JsonView(SystemViews.GroupBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ManufacturerGroup createUser(@RequestBody ManufacturerGroup group){
        return groupRepository.save(group);
    }

    @JsonView(SystemViews.GroupBasicView.class)
    @GetMapping
    public Page<ManufacturerGroup> getAll(@RequestParam Map<String,String> allParams){
        return groupRepository.getAll(allParams);
    }

    @JsonView(SystemViews.GroupDetailedView.class)
    @GetMapping("/{id}")
    public ManufacturerGroup get(@PathVariable Long id){
        return groupRepository.get(id);
    }

    @GetMapping("/types")
    public List<GroupType> getTypes(){
        return groupRepository.getGroupTypeList();
    }

    @JsonView(SystemViews.GroupDetailedView.class)
    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ManufacturerGroup update(@PathVariable Long id, @RequestBody ManufacturerGroup group) throws JsonProcessingException {
        return groupRepository.update(id,group);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        groupRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Grupo eliminado con exito");
    }
}
