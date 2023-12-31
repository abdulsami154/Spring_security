package com.example.Security.controller;
import com.example.Security.dto.AuthenticationResponse;
import com.example.Security.dto.LoginCredentials;
import com.example.Security.dto.UserDto;
import com.example.Security.service.JwtUtil;
import com.example.Security.service.MyUserDetailService;
import com.example.Security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

        @Autowired
        UserService userService;
        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        MyUserDetailService myUserDetailService;
        @Autowired
        JwtUtil jwtUtill;



        /**
         * This will generate token for user
         * @param loginCredentials
         * @return
         */

        @PostMapping("/token")
        public ResponseEntity<AuthenticationResponse> generateToken(@RequestBody LoginCredentials loginCredentials){
                this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginCredentials.getEmail(),loginCredentials.getPassword()));

                UserDetails userDetails=myUserDetailService.loadUserByUsername(loginCredentials.getEmail());
                String jwtToken=jwtUtill.generateToken(userDetails);

                return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
        }

        /**
         * This will register user
         * @param userDto
         * @return
         */

        @PostMapping("/user")
        public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
                return ResponseEntity.ok(userService.registerUser(userDto));
        }

        /**
         * This will get all user
         * @return
         */
        @GetMapping("/user")
        public ResponseEntity<List<UserDto>> getAllUser(){
            return ResponseEntity.ok(userService.getAllUser());
        }

        /**
         * This will getUser by id
         * @param id
         * @return
         */
        @GetMapping("/user/{id}")
        public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
                return ResponseEntity.ok(userService.getUserById(id));
        }

        @PreAuthorize("hasRole('ROLE_USER')")
        @GetMapping("/test1")
        public ResponseEntity<String> tester(){
                return ResponseEntity.ok("Only User can access this api");
        }


        @PreAuthorize("hasRole('ROLE_ADMIN')")
        @GetMapping("/test2")
        public ResponseEntity<String> tester2(){
                return ResponseEntity.ok("Only Admin can access this api");
        }

}
