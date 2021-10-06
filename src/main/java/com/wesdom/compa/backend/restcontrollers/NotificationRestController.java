package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.users.Notification;
import com.wesdom.compa.backend.database.repositories.INotificationRepository;
import com.wesdom.compa.backend.dtos.GeneralResponse;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import com.wesdom.compa.backend.service.interfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("v1/notification")
public class NotificationRestController {

    @Autowired
    private INotificationRepository notificationRepository;

    @Autowired
    private INotificationService notificationService;


    @GetMapping
    @JsonView(SystemViews.NotificationBasicView.class)
    public Page<Notification> getAll(@RequestParam Map<String,String> allParams){
        return notificationRepository.getAll(allParams);
    }

    @GetMapping("/{id}")
    @JsonView(SystemViews.NotificationBasicView.class)
    public Notification get(@PathVariable Long id){
        return notificationRepository.get(id);
    }

    @DeleteMapping(value = "/{ids}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public GeneralResponse delete(@PathVariable List<Long> ids) throws JsonProcessingException {
        if(ids != null && !ids.isEmpty()){
            notificationRepository.delete(ids);
            return new GeneralResponse().setErrorCode("000").setResponse("Notificaciones eliminadas con exito");
        }
        return new GeneralResponse().setErrorCode("000").setResponse("ids no provistos");
    }

    @PostMapping("/MG/{mgId}/estateSegment/{esId}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public GeneralResponse createMGEstateSegmentNotification(
            @PathVariable Long mgId,
            @PathVariable Long esId
    ){
        notificationService.createMGEstateSegmentNotification(mgId, esId);
        return new GeneralResponse().setErrorCode("000").setResponse("Notificacion creada con exito");
    }

    @PostMapping("/MG/{mgId}/manufacturer/{manId}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public GeneralResponse createMGManufacturerNotification(
            @PathVariable Long mgId,
            @PathVariable Long manId
    ){
        notificationService.createMGManufacturerNotification(mgId, manId);
        return new GeneralResponse().setErrorCode("000").setResponse("Notificacion creada con exito");
    }

    @PostMapping("/productionFinished/{productInStateSegmentId}/{promoterId}/{associationId}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public GeneralResponse createProductionFinishedNotification(
           @PathVariable Long productInStateSegmentId,
           @PathVariable Long promoterId,
           @PathVariable Long associationId
    ){
        notificationService.createProductionFinishedNotification(
                productInStateSegmentId,
                promoterId,
                associationId
        );
        return new GeneralResponse().setErrorCode("000").setResponse("Notificacion creada con exito");
    }
}
