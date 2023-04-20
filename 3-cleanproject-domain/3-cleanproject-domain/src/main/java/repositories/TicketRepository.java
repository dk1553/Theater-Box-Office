package repositories;

import businessObjects.Event;
import businessObjects.Ticket;

import java.util.ArrayList;
import java.util.List;

public interface TicketRepository {
    void save(Ticket ticket) throws Exception;
    List<Ticket> findAllTickets();
    List<Ticket> findTicketsOfEvent(String eventID);
    void addTickets(ArrayList<Event> events);
    Ticket findTicketById(String id);
    Boolean isEmpty();
}
