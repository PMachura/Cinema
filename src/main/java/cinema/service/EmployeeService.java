package cinema.service;

import cinema.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    public Employee findOne(Integer id) {
        return employeeRepository.findOne(id);
    }
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
    

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void delete(Integer id) {
        employeeRepository.delete(id);
    }

    public List<Employee> filteredFindAll(Map<String, String> params) {

        if (params.isEmpty()) {
            return employeeRepository.findAll();
        }

        List<Employee> employees = new ArrayList<Employee>(0);
        if (params.containsKey("email")) {
            employees.addAll(findByEmails(params.get("email")));
        }
        return employees;
    }

    public List<Employee> findByEmails(String emails) {
        List<Employee> employees = new ArrayList<Employee>();
        for (String email : emails.split(",")) {
            Employee employee = employeeRepository.findByEmail(email);
            if (employee != null) {
                employees.add(employee);
            }
        }
        return employees;
    }

}
