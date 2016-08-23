package cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{

}
