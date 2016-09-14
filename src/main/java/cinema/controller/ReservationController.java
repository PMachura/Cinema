/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.controller;

import cinema.form.TicketSessionForm;
import cinema.form.UserSessionForm;
import cinema.model.Seat;
import cinema.model.User;
import cinema.service.HallService;
import cinema.service.MovieService;
import cinema.service.SeatService;
import cinema.service.ShowingService;
import cinema.service.TicketService;
import cinema.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Przemek
 */
@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private MovieService movieService;
    private ShowingService showingService;
    private HallService hallService;
    private SeatService seatService;
    private UserService userService;
    private TicketService ticketService;

    private TicketSessionForm ticketSessionForm;
    private UserSessionForm userSessionForm;

    @Autowired
    public ReservationController(MovieService movieService, ShowingService showingService, HallService hallService, SeatService seatService, UserService userService,
            TicketSessionForm ticketSessionForm, UserSessionForm userSessionForm, TicketService ticketService) {
        
        this.movieService = movieService;
        this.showingService = showingService;
        this.hallService = hallService;
        this.seatService = seatService;
        this.userService = userService;
        this.ticketSessionForm = ticketSessionForm;
        this.userSessionForm = userSessionForm;
        this.ticketService = ticketService;
    }

    @RequestMapping("/movie")
    public String pickMovie(Model model) {
        model.addAttribute("movies", movieService.findAll());
        return "reservation/movies";
    }

    @RequestMapping(value = "/showing", params = "movie", method = RequestMethod.POST)
    public String pickShowing(Model model, @RequestParam Integer movieId) {  
        ticketSessionForm.setMovie(movieService.findOne(movieId));
        model.addAttribute("showings", showingService.findByMovieId(movieId));
        return "reservation/showings";
    }

    @RequestMapping(value = "/seats", params = "showing",  method = RequestMethod.POST)
    public String pickSeats(Model model, @RequestParam Integer showingId) {
        ticketSessionForm.setShowing(showingService.findOne(showingId));
        ticketSessionForm.getShowing().setMovie(movieService.findOne(ticketSessionForm.getMovie().getId()));

        List<Seat> seats = seatService.findEmptySeatsForShowing(ticketSessionForm.getShowing().getHall().getId(), showingId);
        model.addAttribute("seats", seats);
        return "reservation/seats";
    }

    @RequestMapping(value = "/user", params = "seats", method = RequestMethod.POST)
    public String userInformation(Model model, @RequestParam String seatsId) {

        String[] seatsIdString = seatsId.split(",");
        Integer[] seatsIdInteger = new Integer[seatsIdString.length];
        for (int i = 0; i < seatsIdInteger.length && i < seatsIdString.length; i++) {
            seatsIdInteger[i] = Integer.parseInt(seatsIdString[i]);
        }
        
        ticketSessionForm.setSeats(seatService.findByIdIn(seatsIdInteger));
        model.addAttribute("user", userSessionForm.toUser());

        return "reservation/user";

    }

    @RequestMapping(value = "/confirmation", params = "user", method = RequestMethod.POST)
    public String confirmation(Model model, @ModelAttribute @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "reservation/user";
        }
        
        ticketSessionForm.setUser(user);
        model.addAttribute("ticket", ticketSessionForm.toTicket());

        return "reservation/confirmation";

    }

    @RequestMapping(value = "/finalize", method = RequestMethod.POST, params = "confirm")
    public String finalization() {
        if (ticketSessionForm.getUser().getId() == null) {
            userService.save(ticketSessionForm.getUser());
        }
        ticketService.save(ticketSessionForm.toTicket());
        ticketSessionForm = new TicketSessionForm();
        return "redirect:/reservation/successfull";
    }

    @RequestMapping("/successfull")
    public String successMessage() {
        return "/reservation/successMessage";
    }

}
