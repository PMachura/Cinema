/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.controller;

import cinema.model.Showing;
import cinema.service.HallService;
import cinema.service.MovieService;
import cinema.service.ShowingService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Przemek
 */
@Controller
@RequestMapping("/showing")
public class ShowingController {

    private ShowingService showingService;
    private MovieService movieService;
    private HallService hallService;

    @Autowired
    ShowingController(ShowingService showingService, MovieService movieService, HallService hallService) {
        this.showingService = showingService;
        this.movieService = movieService;
        this.hallService = hallService;
    }

    @RequestMapping
    public String index(Model model, @RequestParam(required = false) Map<String,String> params) {
        model.addAttribute("showings", showingService.filteredFindAll(params));
        return "showing/index";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("showing", new Showing());
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("halls", hallService.findAll());
        return "showing/showingForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid Showing showing, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "showing/showingForm";
        }
        showingService.save(showing);
        return "redirect:/showing/" + showing.getId() + "/show";
    }

    @RequestMapping(value = "/{id}/show")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute(showingService.findOne(id));
        return "showing/showing";
    }

    @RequestMapping(value = "/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        System.out.println("@@@@@ JESTEM W DELETE");
        showingService.delete(id);
        redirectAttributes.addFlashAttribute("operationResultMessage", "Showing has been deleted");
        return "redirect:/showing";
    }
    
    @RequestMapping(value = "/{id}/edit")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("showing", showingService.findOne(id));
        model.addAttribute("halls", hallService.findAll());
        model.addAttribute("movies", movieService.findAll());
        return "showing/showingForm";
    }
}
