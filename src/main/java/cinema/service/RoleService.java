package cinema.service;

import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}
}
