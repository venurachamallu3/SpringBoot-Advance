package com.ExceptionDTO.ExceptionDTO.Config;

import com.ExceptionDTO.ExceptionDTO.Entity.OwnerUsers;
import com.ExceptionDTO.ExceptionDTO.Repository.OwnerUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private OwnerUserRepo ownerUserRepo;
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (isValidUser(username, password)) {
            return new UsernamePasswordAuthenticationToken(username, password);
        } else {
            throw new AuthenticationException("Invalid credentials") {
            };
        }
    }

    private boolean isValidUser(String username, String password) {
        OwnerUsers user = ownerUserRepo.findByUsername(username).get();
        return user.getUsername().equals(username) && passwordEncoder.matches(password, user.getPassword());
    }
}
