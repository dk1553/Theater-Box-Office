package repositories;

import businessObjects.Event;
import businessObjects.TheaterBuilding;
import businessObjects.Ticket;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TicketRepository {
    Ticket buyTicket(String ticket);
    ArrayList<Ticket> findAllTickets();
    void loadTicketsFromDB(TheaterBuilding theaterBuilding) throws Exception;
    void addTickets(ArrayList<Event> events);
}
