package com.ExceptionDTO.ExceptionDTO.Repository;

import com.ExceptionDTO.ExceptionDTO.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
