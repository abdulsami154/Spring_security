package com.example.Security.service;


import com.example.Security.Repository.UserRepo;
import com.example.Security.dto.CustomUserDetail;
import com.example.Security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    UserRepo userRepo;

    public User getLoggedInUser(){
    Object principle= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if(principle instanceof CustomUserDetail){
            Long id=((CustomUserDetail)principle).getUserId();
        Optional<User> user=userRepo.findById(id);
            if(user.isPresent()){
                return user.get();
            }
    }
    throw new RuntimeException("USER NOT FOUND");
 }
}
