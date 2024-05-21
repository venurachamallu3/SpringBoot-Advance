package com.ExceptionDTO.ExceptionDTO.Controller;


import com.ExceptionDTO.ExceptionDTO.DTO.UserDTO;
import com.ExceptionDTO.ExceptionDTO.Entity.User;
import com.ExceptionDTO.ExceptionDTO.Exception.UserNotFoundException;
import com.ExceptionDTO.ExceptionDTO.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;

//    @Autowired
//    private UserDTO userDTO;



//    @Autowired
//    public ModelMapper modelMapper;

    @PostMapping("create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        System.out.println("name si "+user.getName());
       User us= userService.createUser(user);
        return  new ResponseEntity<>(us, HttpStatus.CREATED);
    }


    @GetMapping("show-users")
    public ResponseEntity<List<User>> getAllUsers(){
       List<User> allUsers= userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }



    @GetMapping("show-user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id){
        Optional<UserDTO> user = userService.getUserById(id);
        if (user.isPresent()) {
//            UserDTO userDTO = modelMapper.map(user.get(), UserDTO.class);
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
       return userService.deleteUserById(id);
    }

}
