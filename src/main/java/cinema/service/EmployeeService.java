package cinema.service;

import cinema.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.EmployeeRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
        
        public Employee findOne(Integer id){
            return employeeRepository.findOne(id);
        }
        
        public List<Employee> findAll(){
            return employeeRepository.findAll();
        }
        
        public Employee save(Employee employee){
            return employeeRepository.save(employee);
        }
        
        public void delete(Integer id){
            employeeRepository.delete(id);
        }
	
	
}
