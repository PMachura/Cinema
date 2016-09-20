package cinema.service;

import cinema.exception.EntityDuplicateException;
import cinema.model.Employee;
import cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    public User save(User user) throws EntityDuplicateException {
        if(exists(user)) 
                throw new EntityDuplicateException("User save: duplicate entity");
        return userRepository.save(user);
    }

    public User findOne(Integer id) {
        return userRepository.findOne(id);
    }

    public boolean existsByEmail(User user) {
        boolean exists = false;
        if (user.getEmail() != null) {
            exists = userRepository.findByEmail(user.getEmail()) != null;
        }

        return exists;
    }

    public boolean existsById(User user) {
        return user.getId() != null ? userRepository.exists(user.getId()) : false;
    }

    public boolean exists(User user) {
        return (existsById(user) || existsByEmail(user));
    }

    public void delete(Integer id) {
        userRepository.delete(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    public boolean compareUser(User first, User second) {
        return first.getId() == second.getId()
                && first.getEmail().equals(second.getEmail())
                && first.getFirstName().equals(second.getFirstName())
                && first.getLastName().equals(second.getLastName());
    }

    public User saveOrIfExistsSetId(User user) {
        User founded = userRepository.findByEmail(user.getEmail());
        return founded == null ? userRepository.save(user) : founded;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> filteredFindAll(Map<String, String> params) {

        if (params.isEmpty()) {
            return userRepository.findAll();
        }

        List<User> users = new ArrayList<User>(0);
        if (params.containsKey("email")) {
            users.addAll(findByEmails(params.get("email")));
        }
        return users;
    }

    public List<User> findByEmails(String emails) {
        List<User> users = new ArrayList<User>();
        for (String email : emails.split(",")) {
            User user = userRepository.findByEmail(email);
            if (user != null) {
                users.add(user);
            }
        }
        return users;
    }
}
