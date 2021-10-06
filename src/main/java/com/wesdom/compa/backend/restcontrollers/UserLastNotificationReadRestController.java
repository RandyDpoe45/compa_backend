package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.model.users.UserLastNotificationRead;
import com.wesdom.compa.backend.database.repositories.IUserLastNotificationReadRepository;
import com.wesdom.compa.backend.dtos.GeneralResponse;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import com.wesdom.compa.backend.service.interfaces.IFloraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("v1/userLastNotificationRead")
public class UserLastNotificationReadRestController {
    
    @Autowired
    private IUserLastNotificationReadRepository userLastNotificationReadRepository;

    @Autowired
    private IFloraService floraService;

    @PostMapping
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @JsonView(SystemViews.UserLastNotificationReadBasicView.class)
    public UserLastNotificationRead create(@RequestBody UserLastNotificationRead userLastNotificationRead){
        return userLastNotificationReadRepository.save(userLastNotificationRead);
    }

    @GetMapping("/user/{authUserId}")
    @JsonView(SystemViews.UserLastNotificationReadBasicView.class)
    public UserLastNotificationRead getByAuthUserId(@PathVariable Long authUserId){
        return userLastNotificationReadRepository.getByAuthUserId(authUserId);
    }

    @GetMapping("/{id}")
    @JsonView(SystemViews.UserLastNotificationReadBasicView.class)
    public UserLastNotificationRead get(@PathVariable Long id){
        return userLastNotificationReadRepository.get(id);
    }

    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @JsonView(SystemViews.UserLastNotificationReadBasicView.class)
    public UserLastNotificationRead update(
            @PathVariable Long id,
            @RequestBody UserLastNotificationRead userLastNotificationRead
    ) throws JsonProcessingException {
        return userLastNotificationReadRepository.update(id,userLastNotificationRead);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public GeneralResponse delete(@PathVariable Long id) throws JsonProcessingException {
        floraService.deleteFlora(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Indice borrado con exito");
    }
}
