/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.controller;

import cinema.exception.EntityDuplicateException;
import cinema.model.Movie;
import cinema.model.User;
import cinema.service.TicketService;
import cinema.service.UserService;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Przemek
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private TicketService ticketService;

    @Autowired
    public UserController(UserService userService, TicketService ticketService) {
        this.userService = userService;
        this.ticketService = ticketService;
    }
    
    @ExceptionHandler(EntityDuplicateException.class)
    public ModelAndView IOExceptionHandle(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("user/userForm");
        modelAndView.addObject("error", "Podany email już istnieje");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
   
    @RequestMapping(value = "/myProfile")
    public String myProfile(Model model, Authentication authentication){
        model.addAttribute("user",userService.findByEmail(authentication.getName()));
        return "user/profile";
    }
    
    @RequestMapping(value = "/deleteMyProfile")
    public String deleteMyProfile(Authentication authentication){
        userService.deleteByEmail(authentication.getName());
        return "redirect:/logout";
    }
    
    @RequestMapping(value = "/editMyProfile")
    public String editMyProfile(Authentication authentication, Model model){
        model.addAttribute("user",userService.findByEmail(authentication.getName()));
        return "user/userForm";
    }
    
    @RequestMapping(value = "/myTickets")
    public String myTickets(Authentication authentication, Model model){
        model.addAttribute("tickets",ticketService.findByUserEmail(authentication.getName()));
        return "ticket/index";
    }
    
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "user/userForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String create(@Valid User user, BindingResult bindingResult) throws EntityDuplicateException {
        if (bindingResult.hasErrors()) {
            return "user/userForm";
        }
        userService.save(user);
        return "redirect:/user/myProfile";
    }

    @RequestMapping
    public String index(Model model, @RequestParam(required = false) Map<String,String> params) {
        model.addAttribute("users", userService.filteredFindAll(params));
        return "user/index";
    }

    @RequestMapping("/{id}/show")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "user/user";
    }

    @RequestMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "user/userForm";
    }

    @RequestMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        userService.delete(id);
        redirectAttributes.addFlashAttribute("operationResultMessage", "User has been deleted");
        return "redirect:/user";
    }
}
