package com.example.store.service;

import com.example.store.dao.SystemUserDao;
import com.example.store.entity.SystemUser;
import com.example.store.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Jeff
 */
@Service
public class SystemUserService implements UserDetailsService {
    @Autowired
    SystemUserDao systemUserDao;

    public SystemUser getUserByAccount(String account) {
        return systemUserDao.findByAccount(account).orElseThrow(
                () -> new ResourceNotFoundException("Can't find user."));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return getUserByAccount(username);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("Username is wrong.");
        }
    }
}