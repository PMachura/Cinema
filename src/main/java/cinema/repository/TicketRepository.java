package cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.model.Ticket;

public interface TicketRepository extends JpaRepository <Ticket,Integer> {

}
