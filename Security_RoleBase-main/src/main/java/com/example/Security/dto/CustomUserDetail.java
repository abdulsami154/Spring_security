package com.example.Security.dto;

import com.example.Security.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetail implements UserDetails {



    private User user;
    public CustomUserDetail(User user){
        this.user=user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorityList=new ArrayList<>();
            user.getRoles().stream().forEach(roles -> authorityList.add(new SimpleGrantedAuthority(roles.getName())));
            return authorityList;
    }


    public Long getUserId(){
        return this.user.getId();
    }
    @Override
    public String getPassword() {
        return this.user.getPassword();
    }


    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
