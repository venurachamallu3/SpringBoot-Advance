package com.ExceptionDTO.ExceptionDTO.Repository;


import com.ExceptionDTO.ExceptionDTO.Entity.OwnerUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Repository
public interface OwnerUserRepo  extends JpaRepository <OwnerUsers,Long> {

    Optional<OwnerUsers> findByUsername(String username);

    boolean existsByEmail(String email);
    Optional<OwnerUsers> findByUsernameOrEmail(String Username , String Email);
}
