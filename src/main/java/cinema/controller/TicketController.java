/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.controller;

import cinema.service.TicketService;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String index(Model model, @RequestParam(required = false) Map<String,String> params){
        model.addAttribute("tickets", ticketService.filteredFindAll(params));
        return "ticket/index";
    }
    
    @RequestMapping("/{id}/show")
    public String show(@PathVariable Integer id, Model model){
       model.addAttribute("ticket",ticketService.findOne(id));
       return "ticket/ticket";
    }
    
    @RequestMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        ticketService.delete(id);
        redirectAttributes.addFlashAttribute("operationResultMessage","Ticket has been deleted");
        return "redirect:/ticket";
    }
}
