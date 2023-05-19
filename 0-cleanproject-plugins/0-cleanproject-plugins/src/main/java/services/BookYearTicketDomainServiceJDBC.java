package services;

import vo.Price;
import entities.Ticket;
import entities.YearTicket;
import repositories.TicketRepository;

import java.util.ArrayList;
import java.util.List;

public class BookYearTicketDomainServiceJDBC implements BookYearTicketDomainService {
    @Override
    public Ticket bookTicket(TicketRepository ticketRepository) throws Exception {

        YearTicket ticket = new YearTicket(new Price("500"));
        try {
            ticket.setBooked();
            List<Ticket> tickets= new ArrayList<>();
            tickets.add(ticket);
            ticketRepository.addTickets( tickets);
            return ticket;
        } catch (Exception e) {
            return null;
        }
    }
}
