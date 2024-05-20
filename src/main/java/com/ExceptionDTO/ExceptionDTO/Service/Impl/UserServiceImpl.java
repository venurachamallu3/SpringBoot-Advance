package com.ExceptionDTO.ExceptionDTO.Service.Impl;

import com.ExceptionDTO.ExceptionDTO.DTO.UserDTO;
import com.ExceptionDTO.ExceptionDTO.Entity.User;
import com.ExceptionDTO.ExceptionDTO.Exception.UserNotFoundException;
import com.ExceptionDTO.ExceptionDTO.Repository.UserRepository;
import com.ExceptionDTO.ExceptionDTO.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDTO userDTO;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

    @Override
    public Optional<UserDTO> getUserById(Long Id) {

//        Optional<User> user = Optional.of(userRepository.findById(Id).get());


        Optional<User> user= userRepository.findById(Id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User With "+Id+" is not Found ...");
        }


        UserDTO userDTO = modelMapper.map(user.get(),UserDTO.class);
        return Optional.ofNullable(userDTO);

    }

    @Override
    public User createUser(User user) {
        System.out.println("name is "+user.getName());
        return userRepository.save(user);
    }

    @Override
    public String deleteUserById(Long Id) {
        userRepository.deleteById(Id);
        return  "Successfully Deleted....";
    }
}
