package com.ExceptionDTO.ExceptionDTO.Config;


import com.ExceptionDTO.ExceptionDTO.Entity.OwnerUsers;
import com.ExceptionDTO.ExceptionDTO.Repository.OwnerUserRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private OwnerUserRepo ownerUserRepo;
    @Override
    public UserDetails loadUserByUsername(String usernameorEmail) throws UsernameNotFoundException {

        OwnerUsers ownerUsers= ownerUserRepo.findByUsernameOrEmail(usernameorEmail,usernameorEmail).get();

        System.out.println(ownerUsers.getUsername()+ "  " +ownerUsers.getPassword());

        Set<GrantedAuthority> authorities = ownerUsers.getRole().stream().
                map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(usernameorEmail,
                ownerUsers.getPassword(),
                authorities);
    }
}
