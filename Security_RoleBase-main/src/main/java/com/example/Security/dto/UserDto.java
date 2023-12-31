package com.example.Security.dto;

import com.example.Security.model.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class UserDto {

    private String name;
    private String email;
    private String number;

    @JsonIgnore
    private Set<Roles> roles = new HashSet<Roles>();

}
