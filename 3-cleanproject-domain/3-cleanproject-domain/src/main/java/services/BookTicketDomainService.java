package services;

import businessObjects.Ticket;
import repositories.TicketRepository;

public interface BookTicketDomainService {


     Ticket bookTicket(TicketRepository ticketRepository, String ticketID) ;

     boolean checkPossibilityToBook(Ticket ticket);

     Ticket findTicket(TicketRepository ticketRepository, String ticket) ;

}
