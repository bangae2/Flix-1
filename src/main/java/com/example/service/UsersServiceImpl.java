package com.example.service;

import com.example.dao.UsersDao;
import com.example.entity.AuthEntity;
import com.example.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

/**
 * Created by bangae11 on 2016-06-14.
 */
@Service
public class UsersServiceImpl implements UsersService,Serializable {

    @Autowired
    private UsersDao usersDao;

    public void usersRegister(UsersEntity usersEntity) {
        this.usersDao.usersRegister(usersEntity);
        AuthEntity authEntity = new AuthEntity();
        authEntity.setId(usersEntity.getId());
        authEntity.setRole("ROLE_USER");
        this.usersDao.autoAuth(authEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity  usersEntity = this.usersDao.loadUserByUsername(username);
        List<AuthEntity> auths= usersEntity.getAuthEntities();
        HashSet<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(AuthEntity authEntity : auths) {
            final String role = authEntity.getRole();
            authorities.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return role;
                }
            });
        }
        usersEntity.setAuthorities(authorities);
        return usersEntity;
    }
}
