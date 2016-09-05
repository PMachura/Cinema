package cinema.service;

import cinema.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.EmployeeRepository;
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
	
	
}
