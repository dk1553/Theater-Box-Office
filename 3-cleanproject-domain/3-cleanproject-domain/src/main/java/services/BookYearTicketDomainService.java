package services;

import businessObjects.Ticket;
import repositories.TicketRepository;

public interface BookYearTicketDomainService {
    Ticket bookTicket(TicketRepository ticketRepository) throws Exception;
}
