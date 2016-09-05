/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.controller;

import cinema.model.User;
import cinema.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Przemek
 */

@Controller
@RequestMapping("/user")
public class UserController {
    
    private UserService userService;
    
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String registerForm(Model model){
        model.addAttribute("user", new User());
        return "user/userForm";
    }
    
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String create(@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "user/userForm";
        }
        userService.save(user);
        return "redirect:/user/show/" + user.getId();
    }
    
    @RequestMapping(value="/show/{id}", method = RequestMethod.GET)
    public String getProfile(@PathVariable Integer id, Model model){
        User user = userService.findOne(id);
        model.addAttribute("user",user);
        return "user/user";
    }
}
