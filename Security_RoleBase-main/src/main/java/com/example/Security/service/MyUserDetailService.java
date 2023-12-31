package com.example.Security.service;

import com.example.Security.Repository.UserRepo;
import com.example.Security.dto.CustomUserDetail;
import com.example.Security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailService implements UserDetailsService {

        @Autowired
        UserRepo userRepo;

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            User user=userRepo.findByEmail(email);
            if(user == null){
                throw new UsernameNotFoundException("Invalid Email ! No User Exist !");
            }
            return new CustomUserDetail(user);
        }
}
