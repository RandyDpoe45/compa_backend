package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.JWTAuthenticationUtils;
import com.wesdom.compa.backend.database.model.AuthUser;
import com.wesdom.compa.backend.database.repositories.IAuthUserRepository;
import com.wesdom.compa.backend.dtos.GeneralResponse;
import com.wesdom.compa.backend.dtos.UserLoginDto;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import com.wesdom.compa.backend.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.compa.backend.exceptionhandling.exceptions.GeneralException;
import com.wesdom.compa.backend.service.interfaces.IAuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.wesdom.compa.backend.JWTConstants.HEADER_AUTHORIZACION_KEY;
import static com.wesdom.compa.backend.JWTConstants.TOKEN_BEARER_PREFIX;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("v1/authUser")
public class AuthUserRestController {

    @Autowired
    private IAuthUserRepository userRepository;

    @Autowired
    private JWTAuthenticationUtils jWTAuthenticationUtils;
    
    @Autowired
    private IAuthUserService userService;

    @PostMapping
    @JsonView(SystemViews.AuthUserBasicView.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AuthUser createUser(@RequestBody AuthUser authUser){
        return userService.create(authUser);
    }

    @JsonView(SystemViews.AuthUserBasicView.class)
    @GetMapping
    public Page<AuthUser> getAll(@RequestParam Map<String,String> allParams){
        return userRepository.getAll(allParams);
    }

    @JsonView(SystemViews.AuthUserBasicView.class)
    @GetMapping("/{id}")
    public AuthUser get(@PathVariable Long id){
        return userRepository.get(id);
    }

    @JsonView(SystemViews.AuthUserBasicView.class)
    @PutMapping(value = "/{idAutolineaUser}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AuthUser update(@PathVariable Long idAutolineaUser, @RequestBody AuthUser authUser) throws JsonProcessingException{
        return userService.update(idAutolineaUser,authUser);
    }

    @PutMapping(value = "/password/{idAutolineaUser}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GeneralResponse updatePassword(@PathVariable Long idAutolineaUser, @RequestBody String newPassword) throws JsonProcessingException{
        userService.updateUserPassword(idAutolineaUser,newPassword);
        return new GeneralResponse().setResponse("Contrasena cambiada con exito").setErrorCode("000");
    }

    @JsonView(SystemViews.AuthUserBasicView.class)
    @PostMapping(value = "/login")
    public AuthUser authenticate(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response) throws IOException, ServletException {
        AuthUser au = userRepository.getUserByUserNameOrEmail(userLoginDto.getUsername());
        if(!au.getIsActive()){
            throw new GeneralException(ExceptionCodesEnum.USER_DEACTIVATED,
                    "El usuario esta desactivado, por favor pongase en con el administrador");
        }
        Authentication c = jWTAuthenticationUtils.attemptAuthentication(userLoginDto);
        String token = jWTAuthenticationUtils.createToken(c);
        response.addHeader(HEADER_AUTHORIZACION_KEY,TOKEN_BEARER_PREFIX + " " + token);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        return au;
    }


    @PutMapping(value = "/{idUser}/activate")
    public GeneralResponse activateUser(@PathVariable Long idUser){
        userService.activateUser(idUser);
        return new GeneralResponse("Usuario activado con exito", "000");
    }

    @PutMapping(value = "/{idUser}/deactivate")
    public GeneralResponse deactivateUser(@PathVariable Long idUser){
        userService.deactivateUser(idUser);
        return new GeneralResponse("Usuario desactivado con exito", "000");
    }

}
