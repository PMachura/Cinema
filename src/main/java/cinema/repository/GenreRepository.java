package cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.model.Genre;

public interface GenreRepository extends JpaRepository<Genre,Integer> {

}
