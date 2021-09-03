package com.wesdom.compa.backend.service.interfaces;

import com.wesdom.compa.backend.database.model.users.AuthUser;

public interface IAuthUserService {

    public void updateUserPassword(Long authUserId, String newPassword);
    public AuthUser save(AuthUser authUser);
    public AuthUser update(Long authUserId, AuthUser authUser);
    public AuthUser activateUser(Long authUserId);
    public AuthUser deactivateUser(Long authUserId);
}
