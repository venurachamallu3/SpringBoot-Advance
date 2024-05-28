package com.ExceptionDTO.ExceptionDTO.Controller;


import com.ExceptionDTO.ExceptionDTO.Config.JwtUtil;
import com.ExceptionDTO.ExceptionDTO.DTO.RequestDTO;
import com.ExceptionDTO.ExceptionDTO.DTO.UserDTO;
import com.ExceptionDTO.ExceptionDTO.Entity.User;
import com.ExceptionDTO.ExceptionDTO.Exception.UserNotFoundException;
import com.ExceptionDTO.ExceptionDTO.Service.Impl.OwnerUserImp;
import com.ExceptionDTO.ExceptionDTO.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private OwnerUserImp ownerUserImp;

//    @Autowired
//    private UserDTO userDTO;



//    @Autowired
//    public ModelMapper modelMapper;



    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody RequestDTO requestDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                requestDto.getUsername(),
                requestDto.getPassword()
        );
        System.out.println("TOKEN IN USERNAME AND  PASSWORD AUTH "+token);
        authenticationManager.authenticate(token);
        String jwt = jwtUtil.generate(requestDto.getUsername());
        return ResponseEntity.ok(jwt);
    }

    @PreAuthorize("hasRole(\"ADMIN\")")
    @PostMapping("create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        System.out.println("name si "+user.getName());
       User us= userService.createUser(user);
        return  new ResponseEntity<>(us, HttpStatus.CREATED);
    }


    @PreAuthorize("hasAnyRole(\"ADMIN\",\"USER\")")
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

    @GetMapping("venu")
    public String us(){
        return ownerUserImp.getOwner("venu");
    }

}
