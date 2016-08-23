package cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.model.Showing;

public interface ShowingRepository extends JpaRepository <Showing, Integer> {

}
