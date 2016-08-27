package cinema.service;

import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.EmployeeRepository;

public class EmployeeService {

	EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	
	
}
