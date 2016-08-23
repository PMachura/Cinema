package cinema.service;

import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.TicketRepository;

public class TicketService {

	TicketRepository ticketRepository;

	@Autowired
	public TicketService(TicketRepository ticketRepository) {
		super();
		this.ticketRepository = ticketRepository;
	}
}
