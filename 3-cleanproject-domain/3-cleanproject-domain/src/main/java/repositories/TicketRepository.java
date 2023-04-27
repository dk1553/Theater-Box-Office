package repositories;

import businessObjects.Event;
import businessObjects.Ticket;

import java.util.List;

public interface TicketRepository {
    void save(Ticket ticket) throws Exception;

    List<Ticket> findAllTickets();

    List<Ticket> findTicketsOfEvent(String eventID);

    void addTickets(List<Ticket> tickets);

    Ticket findTicketById(String id);

    Boolean isEmpty();


}
