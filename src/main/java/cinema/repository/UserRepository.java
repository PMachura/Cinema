package cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.model.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User,Integer>{
    public User findByEmail(String email);
    public void deleteByEmail(String email);
}
