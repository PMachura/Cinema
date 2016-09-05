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
import java.nio.file.Paths;
import java.util.Locale;
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
        ModelAndView modelAndView = new ModelAndView("movie/add");
        modelAndView.addObject("error", messageSource.getMessage("upload.io.exception", null, locale));   
        return modelAndView;
    }
    
    @RequestMapping("/uploadError")
    public ModelAndView onUploadError(Locale locale){
        ModelAndView modelAndView = new ModelAndView("movie/add");
        modelAndView.addObject("error", messageSource.getMessage("upload.file.too.big", null, locale));
        return modelAndView;
    }

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("genres", genreService.findAll());
        return "/movie/movieForm";
    }

    @RequestMapping(value = "/create", method = POST)
    public String processRegistration(@ModelAttribute("user") @Valid Movie movie,
            MultipartFile file, Errors errors,
            RedirectAttributes redirectAttributes) throws IOException {

        if (errors.hasErrors()) {
            return "movie/add";
        }
        if (file.isEmpty() || !isImage(file)) {
            redirectAttributes.addFlashAttribute("error", "Niewlasciwy typ pliku");
            return "redirect:/user/register";
        }

        Files.copy(file.getInputStream(), Paths.get(PICTURE_DIR.getFile().getAbsolutePath(), movie.getTitle() + ".jpg"));
        movieService.save(movie);
        return "redirect:/movie/show/" + movie.getId();
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("movie", movieService.findOne(id));
        return "movie/movie";
    }

}
