package services;

import entities.Ticket;
import repositories.TicketRepository;

public interface BookOneWayTicketDomainService {

     Ticket bookTicket(TicketRepository ticketRepository, String ticketID) ;

     boolean checkPossibilityToBook(Ticket ticket);

     Ticket findTicket(TicketRepository ticketRepository, String ticket) ;

}
