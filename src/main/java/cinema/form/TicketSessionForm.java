/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.form;

import cinema.model.Employee;
import cinema.model.Movie;
import cinema.model.Seat;
import cinema.model.Showing;
import cinema.model.Ticket;
import cinema.model.User;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author Przemek
 */


@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TicketSessionForm {
    private Integer id;
    private User user;
    private Movie movie;
    private Showing showing;
    private Employee employee;
    private List<Seat> seats;

    
    public Ticket toTicket(){
        Ticket ticket = new Ticket();
        ticket.setEmployee(employee);
        ticket.setId(id);
        ticket.setSeats(seats);
        ticket.setShowing(showing);
        ticket.setUser(user);
        
        return ticket;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
    
    
    
}
