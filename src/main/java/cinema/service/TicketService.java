package cinema.service;

import cinema.model.Showing;
import cinema.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.TicketRepository;
import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TicketService extends BaseService {

    final int PAGE_LIMIT = 50;
    TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        super();
        this.ticketRepository = ticketRepository;
    }

    public Ticket findOne(Integer id) {
        return ticketRepository.findOne(id);
    }

    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public List<Ticket> findAllDefaultLimit(int page) {
        return ticketRepository.findAll(new PageRequest(page, PAGE_LIMIT)).getContent();
    }

    public void delete(Integer id) {
        ticketRepository.delete(id);
    }
    
    public List<Ticket> findByUserEmail(String email){
        return ticketRepository.findByUserEmail(email);
    }

    public List<Ticket> filteredFindAll(Map<String, String> params) {
        List<Ticket> tickets = ticketRepository.findAll();
        if (params.isEmpty()) {
            return tickets;
        }

        if (params.containsKey("userEmail") && !params.get("userEmail").equals("")) {
            tickets = filterByUsersEmails(tickets, params.get("userEmail"));
        }
        if (params.containsKey("date") && !params.get("date").equals("")) {
            tickets = filterByShowingsDates(tickets, params.get("date"));
        }
        if (params.containsKey("employeeEmail") && !params.get("employeeEmail").equals("")) {
            tickets = filterByEmployeesEmails(tickets, params.get("employeeEmail"));
        }

        return tickets;
    }

    public List<Ticket> filterByShowingsDates(List<Ticket> tickets, String dates) {
        List<Ticket> filteredTickets = new ArrayList<Ticket>();
        for (String date : dates.split(",")) {
            try {
                LocalDate parsedDate = LocalDate.parse(date, dateTimeFormatter);
                for (Ticket ticket : tickets) {
                    if (ticket.getShowing().getDate().equals(parsedDate)) {
                        filteredTickets.add(ticket);
                    }
                }
            } catch (DateTimeParseException exception) {
            }

        }
        return filteredTickets;
    }

    public List<Ticket> filterByUsersEmails(List<Ticket> tickets, String usersEmails) {
        List<Ticket> filteredTickets = new ArrayList<Ticket>();
        for (String userEmail : usersEmails.split(",")) {
            for (Ticket ticket : tickets) {
                if (ticket.getUser().getEmail().equals(userEmail)) {
                    filteredTickets.add(ticket);
                }
            }
        }
        return filteredTickets;
    }

    public List<Ticket> filterByEmployeesEmails(List<Ticket> tickets, String employeesEmails) {
        List<Ticket> filteredTickets = new ArrayList<Ticket>();
        for (String employeeEmail : employeesEmails.split(",")) {
            for (Ticket ticket : tickets) {
                if (ticket.getEmployee().getEmail().equals(employeeEmail)) {
                    filteredTickets.add(ticket);
                }
            }
        }
        return filteredTickets;
    }
}
