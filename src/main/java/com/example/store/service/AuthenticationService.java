package com.example.store.service;


import com.example.store.dao.SystemUserDao;
import com.example.store.entity.AuthenticationRequest;
import com.example.store.entity.RegisterRequest;
import com.example.store.entity.Role;
import com.example.store.entity.SystemUser;
import com.example.store.exceptions.BadRequestException;
import com.example.store.exceptions.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * @author Jeff
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final SystemUserDao systemUserDao;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public SystemUser register(RegisterRequest request) {
        boolean systemUserIsPresence = systemUserDao.findByAccount(request.getAccount()).isPresent();

        if (systemUserIsPresence) {
            throw new BadRequestException("User account is duplicated.");
        }

        SystemUser systemUser = SystemUser.builder()
                .account(request.getAccount())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .role(Role.valueOf(request.getRole()))
                .build();

        return systemUserDao.save(systemUser);
    }

    public SystemUser authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getAccount(),
                            request.getPassword()
                    )
            );
        } catch (Exception exception) {
            throw new UnauthorizedException("Your account or password is either wrong.");
        }

        return systemUserDao.findByAccount(request.getAccount()).orElseThrow();
    }
}
