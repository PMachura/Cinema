/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.service;

import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

/**
 *
 * @author Przemek
 */
@Service
public class AuthenticationService {
    
    public boolean hasRole(Authentication authentication,String role){
        boolean hasRole = false;
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            hasRole = authorities.contains(new SimpleGrantedAuthority(role));
        }
        return hasRole;
    }
    
    public boolean isEmployee(Authentication authentication){
        boolean isEmployee = false;
        if (authentication != null){
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            isEmployee = authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")) 
                      || authorities.contains(new SimpleGrantedAuthority("ROLE_COORDINATOR"))
                      || authorities.contains(new SimpleGrantedAuthority("ROLE_RECEPTIONIST"));                   
        }
        return isEmployee;
    }
    
    public boolean isUser(Authentication authentication){
        boolean isUser = false;
        if(authentication != null){
           Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); 
           isUser = authorities.contains(new SimpleGrantedAuthority("ROLE_USER"));
        }
    
        return isUser;
    }
    
}
