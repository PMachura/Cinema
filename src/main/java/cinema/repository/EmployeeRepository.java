package cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cinema.model.Employee;

public interface EmployeeRepository extends JpaRepository <Employee,Integer>{
    
    public Employee findByEmail(String email);

}
