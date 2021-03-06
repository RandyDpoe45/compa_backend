package com.wesdom.compa.backend.service.interfaces;

import com.wesdom.compa.backend.database.model.AuthUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAuthUserService {

    public void updateUserPassword(Long authUserId, String newPassword);
    public AuthUser create(AuthUser authUser);
    public AuthUser update(Long authUserId, AuthUser authUser);
}
