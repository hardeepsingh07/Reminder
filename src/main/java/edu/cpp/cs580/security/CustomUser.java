package edu.cpp.cs580.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import edu.cpp.cs580.util.Users;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

/**
 * Created by hardeepsingh on 2/17/17.
 */
public class CustomUser  extends Users implements UserDetails {
    private static final long serialVersionUID = 1L;


    public CustomUser(Users users){
        super(users);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

    @Override
    public String getUsername() {
    	
    	return super.getEmail();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean isVerified() {
        return super.isVerified();
    }
}
