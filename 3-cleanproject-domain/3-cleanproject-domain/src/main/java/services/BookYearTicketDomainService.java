package services;

import entities.Ticket;
import repositories.TicketRepository;

public interface BookYearTicketDomainService {
    Ticket bookTicket(TicketRepository ticketRepository) throws Exception;
}
