package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.users.AuthUser;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IAuthUserRepository {
    public AuthUser get(Long id);
    public Page<AuthUser> getAll(Map<String,String> params);
    public AuthUser save(AuthUser authUser);
    public AuthUser update(Long userId, AuthUser authUser);
    public AuthUser updatePassword(Long userId, String newPassword);
    public AuthUser getUserByUserNameOrEmail(String text);
}
