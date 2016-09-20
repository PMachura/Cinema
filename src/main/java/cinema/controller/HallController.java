/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.controller;

import cinema.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Przemek
 */
@Controller
@RequestMapping("/hall")
public class HallController {
    
    private HallService hallService;
    
    @Autowired
    public HallController(HallService hallService){
        this. hallService = hallService;
    }
    
    @RequestMapping("/{id}/show")
    public String show(Model model, @PathVariable Integer id){
       model.addAttribute("hall",hallService.findOne(id));
       return "hall/hall";
    }
}
