package com.example.store.dao;

import com.example.store.entity.SystemUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface SystemUserDao extends CrudRepository<SystemUser, UUID> {
    Optional<SystemUser> findByAccount(String account);
}
