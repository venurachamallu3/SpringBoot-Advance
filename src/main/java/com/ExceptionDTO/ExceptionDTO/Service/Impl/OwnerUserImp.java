package com.ExceptionDTO.ExceptionDTO.Service.Impl;


import com.ExceptionDTO.ExceptionDTO.Entity.OwnerUsers;
import com.ExceptionDTO.ExceptionDTO.Repository.OwnerUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerUserImp {

    @Autowired
    private OwnerUserRepo ownerUserRepo;


    public String getOwner(String name){
        Optional<OwnerUsers> ownerUsers = Optional.of(ownerUserRepo.findByUsername(name).get());
        System.out.println(ownerUsers.get().getUsername());
        return ownerUsers.get().getEmail();
    }

}
