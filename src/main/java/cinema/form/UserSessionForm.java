/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.form;

import cinema.model.Ticket;
import cinema.model.User;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author Przemek
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSessionForm {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Ticket> tickets;
    
    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setTickets(tickets);
        user.setPassword(password);
        return user;
    }
    
    public void saveUser(User user){
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        tickets = user.getTickets();
        password = user.getPassword();
    }
    
    public boolean compare(User user){
        return  user.getId() == this.id &&
                user.getEmail().equals(this.email) &&
                user.getFirstName().equals(this.firstName) &&
                user.getLastName().equals(this.lastName) &&
                user.getPassword().equals(this.password);
                
    }
}
