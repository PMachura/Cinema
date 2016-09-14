/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.controller;

import cinema.model.Employee;
import cinema.service.EmployeeService;
import cinema.service.RoleService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import javax.validation.Valid;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Przemek
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    EmployeeService employeeService;
    RoleService roleService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, RoleService roleService) {
        this.roleService = roleService;
        this.employeeService = employeeService;
    }
   

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employee/index";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("roles", roleService.findAll());
        return "employee/employeeForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employee/employeeForm";
        }
        employeeService.save(employee);
        return "redirect:/employee/" + employee.getId() + "/show";
    }

    @RequestMapping("/{id}/show")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("employee", employeeService.findOne(id));
        return "employee/employee";
    }

    @RequestMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        employeeService.delete(id);
        redirectAttributes.addFlashAttribute("operationResultMessage", "Employee has been deleted");
        return "redirect:/employee";
    }

    @RequestMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("employee", employeeService.findOne(id));
        model.addAttribute("roles", roleService.findAll());
        return "employee/employeeForm";
    }
}
