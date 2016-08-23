package cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.model.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {

}
