package cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>{

}
