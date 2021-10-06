package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.estatesegment.*;
import com.wesdom.compa.backend.database.repositories.IEstateSegmentRepository;
import com.wesdom.compa.backend.database.repositories.INearbyEstateSegmentRepository;
import com.wesdom.compa.backend.database.repositories.INearbyFloraRepository;
import com.wesdom.compa.backend.dtos.GeneralResponse;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import com.wesdom.compa.backend.service.interfaces.IEstateSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

    @Autowired
    private IEstateSegmentService estateSegmentService;

    @Autowired
    private INearbyEstateSegmentRepository nearbyEstateSegmentRepository;

    @Autowired
    private INearbyFloraRepository nearbyFloraRepository;

    @PostMapping
    @JsonView(SystemViews.EstateSegmentDetailView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public EstateSegment createEstateSegment(@RequestBody EstateSegment estateSegment){
        return estateSegmentRepository.save(estateSegment);
    }

    @JsonView(SystemViews.EstateSegmentBasicView.class)
    @GetMapping
    public @ResponseBody  Page<EstateSegment> getAll(@RequestParam Map<String,String> allParams){
        Page<EstateSegment> page = estateSegmentRepository.getAll(allParams);
        return new PageImpl<>(page.getContent(),page.getPageable(), page.getTotalPages()) {};
    }

    @JsonView(SystemViews.EstateSegmentBasicView.class)
    @GetMapping("/be")
    public @ResponseBody  Page<BeekeepingSegment> getAllB(@RequestParam Map<String,String> allParams){
        Page<BeekeepingSegment> page = estateSegmentRepository.getAllBe(allParams);
        return new PageImpl<>(page.getContent(),page.getPageable(), page.getTotalPages()) {};
    }

    @JsonView(SystemViews.EstateSegmentBasicView.class)
    @GetMapping("/ag")
    public @ResponseBody  Page<AgriculturalSegment> getAllA(@RequestParam Map<String,String> allParams){
        Page<AgriculturalSegment> page = estateSegmentRepository.getAllAg(allParams);
        return new PageImpl<>(page.getContent(),page.getPageable(), page.getTotalPages()) {};
    }

    @JsonView(SystemViews.EstateSegmentBasicView.class)
    @GetMapping("/co")
    public @ResponseBody  Page<ConservationSegment> getAllCo(@RequestParam Map<String,String> allParams){
        Page<ConservationSegment> page = estateSegmentRepository.getAllCo(allParams);
        return new PageImpl<>(page.getContent(),page.getPageable(), page.getTotalPages()) {};
    }

    @JsonView(SystemViews.EstateSegmentDetailView.class)
    @GetMapping("/{id}")
    public @ResponseBody EstateSegment get(@PathVariable Long id){
        return estateSegmentRepository.get(id);
    }

    @GetMapping("/types")
    public List<EstateSegmentType> getTypes(){
        return estateSegmentRepository.getEstateSegmentTypeList();
    }

    @JsonView(SystemViews.EstateSegmentDetailView.class)
    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public EstateSegment update(@PathVariable Long id, @RequestBody EstateSegment estateSegment) throws JsonProcessingException {
        return estateSegmentRepository.update(id,estateSegment);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        estateSegmentService.deleteEstateSegment(id);
        return new GeneralResponse("Segmento eliminado con exito","000");
    }

    @PostMapping(value = "/nearbyEstateSegment")
    @JsonView(SystemViews.EstateSegmentBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public NearbyEstateSegment createNearbyEstateSegment(@RequestBody NearbyEstateSegment estateSegment){
        return nearbyEstateSegmentRepository.save(estateSegment);
    }

    @JsonView(SystemViews.EstateSegmentBasicView.class)
    @GetMapping(value = "/nearbyEstateSegment")
    public @ResponseBody  Page<NearbyEstateSegment> getAllNearbyEstateSegment(@RequestParam Map<String,String> allParams){
        Page<NearbyEstateSegment> page = nearbyEstateSegmentRepository.getAll(allParams);
        return new PageImpl<>(page.getContent(),page.getPageable(), page.getTotalPages()) {};
    }

    @JsonView(SystemViews.EstateSegmentBasicView.class)
    @GetMapping("/nearbyEstateSegment/{id}")
    public NearbyEstateSegment getNearbyEstateSegment(@PathVariable Long id){
        return nearbyEstateSegmentRepository.get(id);
    }

    @JsonView(SystemViews.EstateSegmentBasicView.class)
    @PutMapping(value = "/nearbyEstateSegment/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public NearbyEstateSegment updateNearbyEstateSegment(@PathVariable Long id, @RequestBody NearbyEstateSegment estateSegment) throws JsonProcessingException {
        return nearbyEstateSegmentRepository.update(id,estateSegment);
    }

    @DeleteMapping(value = "/nearbyEstateSegment/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public GeneralResponse deleteNearbyEstateSegment(@PathVariable Long id) throws JsonProcessingException {
        nearbyEstateSegmentRepository.delete(id);
        return new GeneralResponse("Segmento cercano eliminado con exito","000");
    }

    @PostMapping(value = "/nearbyFlora")
    @JsonView(SystemViews.NearbyFloraBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public NearbyFlora createNearbyFlora(@RequestBody NearbyFlora nearbyFlora){
        return nearbyFloraRepository.save(nearbyFlora);
    }

    @JsonView(SystemViews.NearbyFloraBasicView.class)
    @GetMapping(value = "/nearbyFlora")
    public @ResponseBody  Page<NearbyFlora> getAllNearbyFlora(@RequestParam Map<String,String> allParams){
        Page<NearbyFlora> page = nearbyFloraRepository.getAll(allParams);
        return new PageImpl<>(page.getContent(),page.getPageable(), page.getTotalPages()) {};
    }

    @JsonView(SystemViews.NearbyFloraBasicView.class)
    @GetMapping("/nearbyFlora/{id}")
    public NearbyFlora getNearbyFlora(@PathVariable Long id){
        return nearbyFloraRepository.get(id);
    }

    @JsonView(SystemViews.NearbyFloraBasicView.class)
    @PutMapping(value = "/nearbyFlora/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public NearbyFlora updateNearbyFlora(@PathVariable Long id, @RequestBody NearbyFlora nearbyFlora) throws JsonProcessingException {
        return nearbyFloraRepository.update(id,nearbyFlora);
    }

    @DeleteMapping(value = "/nearbyFlora/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public GeneralResponse deleteNearbyFlora(@PathVariable Long id) throws JsonProcessingException {
        nearbyFloraRepository.delete(id);
        return new GeneralResponse("Flora cercana eliminada con exito","000");
    }
}
