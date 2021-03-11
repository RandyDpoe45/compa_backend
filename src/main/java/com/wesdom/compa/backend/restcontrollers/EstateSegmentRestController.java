package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.EstateSegment;
import com.wesdom.compa.backend.database.model.EstateSegmentType;
import com.wesdom.compa.backend.database.repositories.IEstateSegmentRepository;
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
@RequestMapping("v1/estateSegment")
public class EstateSegmentRestController {

    @Autowired
    private IEstateSegmentRepository estateSegmentRepository;

    @PostMapping
    @JsonView(SystemViews.EstateSegmentBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public EstateSegment createUser(@RequestBody EstateSegment estateSegment){
        return estateSegmentRepository.create(estateSegment);
    }

    @JsonView(SystemViews.EstateSegmentBasicView.class)
    @GetMapping
    public Page<EstateSegment> getAll(@RequestParam Map<String,String> allParams){
        return estateSegmentRepository.getAll(allParams);
    }

    @JsonView(SystemViews.EstateSegmentBasicView.class)
    @GetMapping("/{id}")
    public EstateSegment get(@PathVariable Long id){
        return estateSegmentRepository.get(id);
    }

    @GetMapping("/types")
    public List<EstateSegmentType> getTypes(){
        return estateSegmentRepository.getEstateSegmentTypeList();
    }

    @JsonView(SystemViews.EstateSegmentBasicView.class)
    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public EstateSegment update(@PathVariable Long id, @RequestBody EstateSegment estateSegment) throws JsonProcessingException {
        return estateSegmentRepository.update(id,estateSegment);
    }
}
