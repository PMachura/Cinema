package cinema.service;

import cinema.model.Role;
import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.RoleRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}
        
        public List<Role> findAll(){
            return roleRepository.findAll();
        }
}
