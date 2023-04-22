package services;

import businessObjects.Ticket;
import repositories.TicketRepository;

import java.util.Objects;

public class BookTicketDomainServiceJDBC implements BookTicketDomainService {
    @Override
    public  Ticket findTicket(TicketRepository ticketRepository, String ticket) {
        if (ticket.equals("")){
            return null;
        }
        return ticketRepository.findTicketById(ticket);
    }
    @Override
    public  Ticket bookTicket(TicketRepository ticketRepository, String ticketID) {
        Ticket ticket =findTicket(ticketRepository, ticketID);
        if (!checkPossibilityToBook(ticket)) {
            return null;
        }
        try {
            ticketRepository.save(ticket);
            ticket.setBooked();
            return ticket;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public  boolean checkPossibilityToBook(Ticket ticket) {
        return (!Objects.isNull(ticket)) && (!ticket.isBooked());
    }


}
