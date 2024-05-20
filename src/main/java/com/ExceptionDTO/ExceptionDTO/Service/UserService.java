package com.ExceptionDTO.ExceptionDTO.Service;

import com.ExceptionDTO.ExceptionDTO.DTO.UserDTO;
import com.ExceptionDTO.ExceptionDTO.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<UserDTO> getUserById(Long Id);

    User createUser(User user);

    String deleteUserById(Long Id);
}
