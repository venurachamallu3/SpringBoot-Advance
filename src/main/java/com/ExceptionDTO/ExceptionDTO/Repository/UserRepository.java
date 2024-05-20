package com.ExceptionDTO.ExceptionDTO.Repository;

import com.ExceptionDTO.ExceptionDTO.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
