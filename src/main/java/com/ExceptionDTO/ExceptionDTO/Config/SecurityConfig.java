package com.ExceptionDTO.ExceptionDTO.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{
        httpSecurity.csrf().disable().
                authorizeHttpRequests((authorize)->{
//                    authorize.requestMatchers(HttpMethod.GET,"/api/users/show-users").hasAnyRole("USER","ADMIN");

                    authorize.anyRequest().authenticated();
//                    authorize.requestMatchers(HttpMethod.POST,"/api/users/create").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.GET,"/api/users/show-users").hasAnyRole("USER","ADMIN");
//                    authorize.requestMatchers(HttpMethod.GET,"/api/users/show-user/**").hasAnyRole("USER","ADMIN");
                }).
        httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails venu = User.builder().username("venu").
//                password(passwordEncoder().encode("Venu@123")).roles("USER").build();
//
//        UserDetails admin = User.builder().username("admin").
//                password(passwordEncoder().encode("Admin@123")).roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(venu,admin);
//    }


}
