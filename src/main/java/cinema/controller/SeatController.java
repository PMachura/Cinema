/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.controller;

import cinema.model.Hall;
import cinema.model.Seat;
import cinema.model.Ticket;
import cinema.repository.EmployeeRepository;
import cinema.repository.SeatRepository;
import cinema.service.EmployeeService;
import cinema.service.HallService;
import cinema.service.SeatService;
import cinema.service.ShowingService;
import cinema.service.TicketService;
import cinema.service.UserService;
import java.util.ArrayList;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Przemek
 */
@Controller
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    SeatRepository seatService;

    @Autowired
    TicketService ticketService;

    @Autowired
    HallService hallService;

    @Autowired
    EmployeeService employeeService;
    
    @Autowired
    ShowingService showingService;
    
    @Autowired
    UserService userService;

    @RequestMapping("/save")
    @Transactional
    public String saveSeat() {

        Seat seat = new Seat();
        seat.setNumber(23);
        seat.setRow(2);

        Hall hall = hallService.findOne(1);
        Ticket ticket = ticketService.findOnd(1);

        seat.setTickets(new ArrayList<Ticket>() {
            {
                add(ticket);
            }
        });
        seat.setHall(hall);

        seatService.save(seat);
        return "home";
    }

    @RequestMapping("/save/ticket")
    public String saveTicket() {
        Ticket ticket = new Ticket();
        ticket.setEmployee(employeeService.findOne(1));
        ticket.setSeats(new ArrayList<Seat>(){{
            add(seatService.findOne(1));
        } 
        });
        ticket.setShowing(showingService.findOne(1));
        ticket.setUser(userService.findOne(1));
        
        ticketService.save(ticket);
        
        return "home";
               
    }
}
