/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.controller;

import cinema.form.UserSessionForm;

import cinema.model.Movie;
import cinema.model.User;
import cinema.service.EmployeeService;
import cinema.service.GenreService;
import cinema.service.HallService;
import cinema.service.MovieService;
import cinema.service.SeatService;
import cinema.service.ShowingService;
import cinema.service.TicketService;
import cinema.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Przemek
 */
@Controller
@RequestMapping("/test")
public class TestController {

    private UserService userService;
    private HallService hallService;
    private TicketService ticketService;
    private EmployeeService employeeService;
    private GenreService genreService;
    private MovieService movieService;
    private SeatService seatService;
    private ShowingService showingService;
    private UserSessionForm userSessionForm;

    @Autowired
    public TestController(UserService userService, HallService hallService, TicketService ticketService, EmployeeService employeeService, GenreService genreService, 
                          MovieService movieService, SeatService seatService, ShowingService showingService,
                          UserSessionForm userSessionForm) {
        this.userService = userService;
        this.hallService = hallService;
        this.ticketService = ticketService;
        this.employeeService = employeeService;
        this.genreService = genreService;
        this.movieService = movieService;
        this.seatService = seatService;
        this.showingService = showingService;
        this.userSessionForm = userSessionForm;
    }
    
    @ModelAttribute("user")
    public User getPerson() {
        return new User();
    }

    @RequestMapping("/form")
    public String form() {
        return "test/registerForm";
    }

    @RequestMapping("/profile")
    public String profile() {
        return "test/profile";
    }


    
    @RequestMapping(value = "/setUser")
    public String setSessionUser(Model model){
        User user = new User();
        user.setEmail("przemek@gmail");
        user.setFirstName("Przemo");
        user.setLastName("Machura");
        user.setId(45);
        userSessionForm.saveUser(user);
        model.addAttribute("user", userSessionForm.toUser());
        return "/test/profile";
    }
    
    @RequestMapping(value="/movie")
    public String showMovie(Model model){
        Movie movie = movieService.findOne(1);
        model.addAttribute("movie", movie);
        
        return "test/movie";
    }
}
