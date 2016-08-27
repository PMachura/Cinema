package cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.model.Hall;

public interface HallRepository extends JpaRepository<Hall,Integer> {

}
