/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.controller.advisor;

import java.util.Locale;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Przemek
 */
@ControllerAdvice
public class GlobalControllersExceptionHandler {
    
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ModelAndView ConstraintViolationExceptionHandle(Locale locale, Exception exception) {      
        ModelAndView modelAndView = new ModelAndView("operationResult");
        modelAndView.addObject("operationResultMessage", 
                "Naruszenie specyfikacji bazy danych. ");
        return modelAndView;
    }
}
