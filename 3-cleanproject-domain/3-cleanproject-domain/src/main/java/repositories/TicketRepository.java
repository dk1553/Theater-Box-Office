package repositories;

import businessObjects.Event;
import businessObjects.TheaterBuilding;
import businessObjects.Ticket;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TicketRepository {
    Ticket buy(Ticket ticket);
    ArrayList<Ticket> findAllTickets();
    ArrayList<Ticket> findTicketsOfEvent( String eventID);
    void addTickets(ArrayList<Event> events);
    Ticket findTicketById(String id);
}
