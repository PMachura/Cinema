/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.controller;

import cinema.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Przemek
 */
@Controller
@RequestMapping("/ticket")
public class TicketController {
    
    TicketService ticketService;
    
    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }
    
    @RequestMapping
    public String index(Model model){
        model.addAttribute("tickets", ticketService.findAllDefaultLimit(0));
        return "ticket/index";
    }
    
    @RequestMapping("/{id}/show")
    public String show(@PathVariable Integer id, Model model){
       model.addAttribute("ticket",ticketService.findOne(id));
       return "ticket/ticket";
    }
}
