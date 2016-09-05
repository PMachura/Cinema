package cinema.service;

import cinema.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        super();
        this.ticketRepository = ticketRepository;
    }
    
    public Ticket findOnd(Integer id){
        return ticketRepository.findOne(id);
    }
    
    public Ticket save(Ticket ticket){
        return ticketRepository.save(ticket);
    }
}
