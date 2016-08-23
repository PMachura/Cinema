package cinema.service;

import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.UserRepository;

public class UserService {
	
	UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
}
