package services;

import entities.Ticket;
import repositories.TicketRepository;
import java.util.Objects;

public class BookOneWayTicketDomainServiceJDBC implements BookOneWayTicketDomainService {
    @Override
    public  Ticket findTicket(TicketRepository ticketRepository, String ticketID) {
        if (ticketID.equals("")){
            return null;
        }
        return ticketRepository.findTicketById(ticketID);
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
