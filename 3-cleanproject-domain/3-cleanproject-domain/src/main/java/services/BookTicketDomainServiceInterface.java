package services;

import businessObjects.Ticket;
import repositories.TicketRepository;

public interface BookTicketDomainServiceInterface {


     Ticket bookTicket(TicketRepository ticketRepository, String ticketID) ;

     boolean possibleToBook(Ticket ticket);

     Ticket findTicket(TicketRepository ticketRepository, String ticket) ;

}
