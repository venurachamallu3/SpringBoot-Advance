package com.ExceptionDTO.ExceptionDTO.Util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
public class PasswordEncoderImp {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("venu"));
        System.out.println(passwordEncoder.encode("admin"));
    }
}
