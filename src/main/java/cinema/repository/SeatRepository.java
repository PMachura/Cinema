package cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.model.Seat;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


public interface SeatRepository extends JpaRepository <Seat, Integer> {

    @Query(value = "select * from Seat s where s.hall_id = ?1 and s.id not in"
            + "(select ts.seat_id from Ticket_Seat ts where ts.ticket_id in"
            + "(select t.id from Ticket t where t.showing_id = ?2))",
            nativeQuery = true)
    public List<Seat> findEmptySeatsForShowing(Integer hallId, Integer showingId);
    
    public List<Seat> findByIdIn(Integer [] ids); 	


}
