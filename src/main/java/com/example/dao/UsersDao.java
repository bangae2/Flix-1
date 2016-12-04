package com.example.dao;

import com.example.entity.AuthEntity;
import com.example.entity.UsersEntity;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by bangae11 on 2016-06-14.
 */
public interface UsersDao {
    public void usersRegister(UsersEntity usersEntity);
    public void autoAuth(AuthEntity authEntity);
    public UsersEntity loadUserByUsername(String username);
    public void resetPassword(String email, String password);
}
