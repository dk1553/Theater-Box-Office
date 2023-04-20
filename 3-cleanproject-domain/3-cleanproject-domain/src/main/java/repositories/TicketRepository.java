package repositories;

import businessObjects.Event;
import businessObjects.Ticket;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TicketRepository {
    void save(Ticket ticket) throws Exception;
    ArrayList<Ticket> findAllTickets();
    ArrayList<Ticket> findTicketsOfEvent( String eventID);
    void addTickets(ArrayList<Event> events);
    Ticket findTicketById(String id);
    Boolean isEmpty();
}
