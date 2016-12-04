package com.example.service;

import com.example.entity.UsersEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by bangae11 on 2016-06-14.
 */
public interface UsersService extends UserDetailsService{
    public void usersRegister(UsersEntity usersEntity);
}
