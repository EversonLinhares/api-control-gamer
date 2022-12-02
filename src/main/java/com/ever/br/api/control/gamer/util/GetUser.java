package com.ever.br.api.control.gamer.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class GetUser {
    public String getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated()){
            return auth.getName();
        }else {
            return "";
        }
    }
    public String getCurrentUserRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated()){
            String role = String.valueOf(auth.getAuthorities().stream().collect(Collectors.toList()).get(0));
            if(role != null){
            return role;
            }
        }
        return "NOROLE";
    }
}
