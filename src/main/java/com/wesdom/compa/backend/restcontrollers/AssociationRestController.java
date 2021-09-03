package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.users.Association;
import com.wesdom.compa.backend.database.model.AssociationMember;
import com.wesdom.compa.backend.database.model.AssociationPromoter;
import com.wesdom.compa.backend.database.repositories.IAssociationMemberRepository;
import com.wesdom.compa.backend.database.repositories.IAssociationPromoterRepository;
import com.wesdom.compa.backend.database.repositories.IAssociationRepository;
import com.wesdom.compa.backend.dtos.GeneralResponse;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import com.wesdom.compa.backend.service.interfaces.IAssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("v1/association")
public class AssociationRestController {
    
    @Autowired
    private IAssociationRepository associationRepository;

    @Autowired
    private IAssociationService associationService;

    @Autowired
    private IAssociationMemberRepository associationMemberRepository;

    @Autowired
    private IAssociationPromoterRepository associationPromoterRepository;

    @PostMapping
    @JsonView(SystemViews.AssociationDetailedView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Association save(@RequestBody Association association){
        return associationRepository.save(association);
    }

    @JsonView(SystemViews.AssociationBasicView.class)
    @GetMapping
    public Page<Association> getAll(@RequestParam Map<String,String> allParams){
        return associationRepository.getAll(allParams);
    }

    @JsonView(SystemViews.AssociationDetailedView.class)
    @GetMapping("/{id}")
    public Association get(@PathVariable Long id){
        return associationRepository.get(id);
    }

    @JsonView(SystemViews.AssociationDetailedView.class)
    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Association update(@PathVariable Long id, @RequestBody Association association) throws JsonProcessingException {
        return associationRepository.update(id,association);
    }

    @PostMapping("/member")
    @JsonView(SystemViews.AssociationMemberBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AssociationMember createMember(@RequestBody AssociationMember associationMember){
        return associationService.addMember(associationMember);
    }

    @JsonView(SystemViews.AssociationMemberBasicView.class)
    @GetMapping("/member")
    public Page<AssociationMember> getAllMember(@RequestParam Map<String,String> allParams){
        return associationMemberRepository.getAll(allParams);
    }

    @JsonView(SystemViews.AssociationMemberBasicView.class)
    @GetMapping("/member/{id}")
    public AssociationMember getMember(@PathVariable Long id){
        return associationMemberRepository.get(id);
    }

    @JsonView(SystemViews.AssociationMemberBasicView.class)
    @PutMapping(value = "/member/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AssociationMember updateMember(@PathVariable Long id, @RequestBody AssociationMember associationMember) throws JsonProcessingException {
        return associationMemberRepository.update(id,associationMember);
    }

    @DeleteMapping(value = "/member/{id}")
    public GeneralResponse deleteMemeber(@PathVariable Long id){
        associationMemberRepository.delete(id);
        return new GeneralResponse("Miembro eliminado con exito","000");
    }

    @PostMapping("/promoter")
    @JsonView(SystemViews.AssociationMemberBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AssociationPromoter createPromoter(@RequestBody AssociationPromoter associationPromoter){
        return associationService.addPromoter(associationPromoter);
    }

    @JsonView(SystemViews.AssociationMemberBasicView.class)
    @GetMapping("/promoter")
    public Page<AssociationPromoter> getAllPromoter(@RequestParam Map<String,String> allParams){
        return associationPromoterRepository.getAll(allParams);
    }

    @JsonView(SystemViews.AssociationMemberBasicView.class)
    @GetMapping("/promoter/{id}")
    public AssociationPromoter getPromoter(@PathVariable Long id){
        return associationPromoterRepository.get(id);
    }

    @JsonView(SystemViews.AssociationMemberBasicView.class)
    @PutMapping(value = "/promoter/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AssociationPromoter updatePromoter(@PathVariable Long id, @RequestBody AssociationPromoter associationPromoter) throws JsonProcessingException {
        return associationPromoterRepository.update(id,associationPromoter);
    }

    @DeleteMapping(value = "/promoter/{id}")
    public GeneralResponse deletePromoter(@PathVariable Long id){
        associationPromoterRepository.delete(id);
        return new GeneralResponse("Promotor eliminado con exito","000");
    }
}
