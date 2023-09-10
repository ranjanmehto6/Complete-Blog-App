package com.demo.blog.config;

import com.demo.blog.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {


private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // You need to provide the authorities/roles for this user
        return null;
    }

    @Override
    public String getPassword() {
        // Return the password for this user
        return user.getPassword(); // Assuming 'User' class has a getPassword() method
    }

    @Override
    public String getUsername() {
        // Return the username for this user
        return user.getUsername(); // Assuming 'User' class has a getUsername() method
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Modify as per your requirements
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify as per your requirements
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify as per your requirements
    }

    @Override
    public boolean isEnabled() {
        return true; // Modify as per your requirements
    }
}
