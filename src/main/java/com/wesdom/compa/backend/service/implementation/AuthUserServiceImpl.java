package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.model.AuthUser;
import com.wesdom.compa.backend.database.repositories.IAuthUserRepository;
import com.wesdom.compa.backend.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.compa.backend.exceptionhandling.exceptions.GeneralException;
import com.wesdom.compa.backend.service.interfaces.IAuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthUserServiceImpl implements IAuthUserService, UserDetailsService {

    @Autowired
    private IAuthUserRepository authUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AuthUser authUser = authUserRepository.getUserByUserNameOrEmail(s);
        if(authUser == null){
            throw new UsernameNotFoundException("No existe usuario");
        }
        if(!authUser.getIsActive()){
            throw new GeneralException(ExceptionCodesEnum.USER_DEACTIVATED,
                    "El usuario esta desactivado, por favor pongase en con el administrador");
        }
        List grantList = new ArrayList();
        return (UserDetails) new User(s,authUser.getPassword(),grantList);
    }

    @Override
    public AuthUser create(AuthUser authUser) {
        AuthUser auxUser = authUserRepository.getUserByUserNameOrEmail(authUser.getEmail());
        if(auxUser != null){
            throw new GeneralException(ExceptionCodesEnum.EMAIL_IN_USE,"El Email ya se encuentra en uso ");
        }
        auxUser =  authUserRepository.getUserByUserNameOrEmail(authUser.getUsername());
        if(auxUser != null){
            throw new GeneralException(ExceptionCodesEnum.USERNAME_IN_USE,"El nombe de usuario ya se encuentra en uso");
        }
        String encodedPassword = authUser.getPassword();
        encodedPassword = passwordEncoder.encode(encodedPassword);
        authUser.setPassword(encodedPassword);
        return authUserRepository.create(authUser);
    }

    @Override
    public AuthUser update(Long authUserId, AuthUser authUser) {
        AuthUser auxUser = authUserRepository.getUserByUserNameOrEmail(authUser.getEmail());
        if(auxUser != null && ! auxUser.getId().equals(authUserId)){
            throw new GeneralException(ExceptionCodesEnum.EMAIL_IN_USE,"El Email ya se encuentra en uso ");
        }
        auxUser =  authUserRepository.getUserByUserNameOrEmail(authUser.getUsername());
        if(auxUser != null && ! auxUser.getId().equals(authUserId)){
            throw new GeneralException(ExceptionCodesEnum.USERNAME_IN_USE,"El nombe de usuario ya se encuentra en uso");
        }
        return authUserRepository.update(authUserId,authUser);
    }

    @Override
    public void updateUserPassword(Long autolineaUserId, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        authUserRepository.updatePassword(autolineaUserId,encodedPassword);
    }
}
