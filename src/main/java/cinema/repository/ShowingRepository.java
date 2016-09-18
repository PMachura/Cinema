package cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.model.Showing;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface ShowingRepository extends JpaRepository <Showing, Integer> {

    @Query("select distinct s from Showing s where s.movie.id = ?1")
    public List<Showing> findByMovieId(Integer id);
    public List<Showing> findByMovieTitle(String title);
    public List<Showing> findByDate(LocalDate date);
    
}
