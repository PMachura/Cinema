/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.controller;

import cinema.model.Movie;
import cinema.service.GenreService;
import cinema.service.MovieService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Przemek
 */
@Controller
@RequestMapping("/movie")
public class MovieController {

    private static final Resource PICTURE_DIR = new DefaultResourceLoader().getResource("/static/moviePictures");
    MovieService movieService;
    GenreService genreService;
    MessageSource messageSource;

    @Autowired
    public MovieController(MovieService movieService, GenreService genreService, MessageSource messageSource) {
        this.movieService = movieService;
        this.genreService = genreService;
        this.messageSource = messageSource;
    }

    private boolean isImage(MultipartFile file) {
        return file.getContentType().startsWith("image");
    }

    @ExceptionHandler(IOException.class)
    public ModelAndView IOExceptionHandle(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("movie/movieForm");
        modelAndView.addObject("error", messageSource.getMessage("upload.io.exception", null, locale));
        modelAndView.addObject("movie", new Movie());
        return modelAndView;
    }

    @RequestMapping("/uploadError")
    public ModelAndView onUploadError(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("movie/add");
        modelAndView.addObject("error", messageSource.getMessage("upload.file.too.big", null, locale));
        return modelAndView;
    }

    @RequestMapping
    public String index(Model model, @RequestParam(required = false) Map<String,String> params) {
        model.addAttribute("movies", movieService.filteredFindAll(params));
        return "/movie/index";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("genres", genreService.findAll());
        return "/movie/movieForm";
    }

    @RequestMapping(value = "/create", method = POST)
    public String processRegistration(@ModelAttribute("user") @Valid Movie movie,
            MultipartFile file, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            return "movie/movieForm";
        }
       
        if (!file.isEmpty()) {
            if (isImage(file)) {
                Files.copy(file.getInputStream(), Paths.get(PICTURE_DIR.getFile().getAbsolutePath(), movie.getTitle() + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
            } else {
                redirectAttributes.addFlashAttribute("error", "Niewłaściwy typ pliku");
                return "redirect:/movie/add";
            }
        }

        movieService.save(movie);
        return "redirect:/movie/" + movie.getId() + "/show";
    }

    @RequestMapping("/{id}/show")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("movie", movieService.findOne(id));
        return "movie/movie";
    }

    @RequestMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("movie", movieService.findOne(id));
        model.addAttribute("genres", genreService.findAll());
        return "/movie/movieForm";
    }

    @RequestMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        movieService.delete(id);
        redirectAttributes.addFlashAttribute("operationResultMessage", "Movie has been deleted");
        return "redirect:/movie";
    }

}
