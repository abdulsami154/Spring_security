package com.example.Security.service;


import com.example.Security.Repository.UserRepo;
import com.example.Security.dto.UserDto;
import com.example.Security.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    ModelMapper modelMapper;


    public List<UserDto> getAllUser() {
        List<User> userList=userRepo.findAll();
        List<UserDto> userDtoList=userList.stream().map(
                user -> modelMapper.map(user , UserDto.class)
        ).collect(Collectors.toList());
        return userDtoList;
    }

    public UserDto getUserById(Long id) {
        Optional<User> user=userRepo.findById(id);
        if(user.isPresent()){
            User user1=user.get();
            return modelMapper.map(user1 , UserDto.class);
        }else{
            throw new RuntimeException("No userFound on id: "+id);
        }


    }



    public UserDto registerUser(UserDto userDto) {
            return null;
    }
}
