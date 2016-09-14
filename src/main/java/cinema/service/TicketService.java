package cinema.service;

import cinema.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.TicketRepository;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service
public class TicketService {
    
    final int PAGE_LIMIT = 50;
    TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        super();
        this.ticketRepository = ticketRepository;
    }
    
    public Ticket findOne(Integer id){
        return ticketRepository.findOne(id);
    }
    
    public Ticket save(Ticket ticket){
        return ticketRepository.save(ticket);
    }
    
    public List<Ticket> findAll(){
        return ticketRepository.findAll();
    }
    
    public List<Ticket> findAllDefaultLimit(int page){
        return ticketRepository.findAll(new PageRequest(page,PAGE_LIMIT)).getContent();
    }
}
