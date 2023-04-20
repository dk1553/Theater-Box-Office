package services;

import businessObjects.Ticket;
import repositories.TicketRepository;

import java.util.Objects;

public class BookTicketDomainService implements BookTicketDomainServiceInterface {
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
        if (!possibleToBook(ticket)) {
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
    public  boolean possibleToBook(Ticket ticket) {
        return (!Objects.isNull(ticket)) && (!ticket.isBooked());
    }


}
