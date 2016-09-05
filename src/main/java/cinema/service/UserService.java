package cinema.service;

import cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findOne(Integer id) {
        return userRepository.findOne(id);
    }

    public boolean compareUser(User first, User second) {
        return first.getId() == second.getId()
                && first.getEmail().equals(second.getEmail())
                && first.getFirstName().equals(second.getFirstName())
                && first.getLastName().equals(second.getLastName());
    }
}
