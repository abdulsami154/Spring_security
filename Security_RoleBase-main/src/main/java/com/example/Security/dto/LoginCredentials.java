package com.example.Security.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class LoginCredentials {

    private String email;
    private String password;

}
