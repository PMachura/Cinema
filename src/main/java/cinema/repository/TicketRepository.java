package cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.model.Ticket;
import java.util.List;

public interface TicketRepository extends JpaRepository <Ticket,Integer> {
    public List<Ticket> findByUserEmail(String email);
}
