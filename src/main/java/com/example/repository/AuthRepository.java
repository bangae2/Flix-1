package com.example.repository;

import com.example.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bangae11 on 2016-06-14.
 */
public interface AuthRepository extends JpaRepository<AuthEntity, Integer> {

}
