package repositories;

import businessObjects.Event;

import java.util.List;

public interface EventRepository {
    List<Event> findAllEvents();

    Event findEventById(String id);

    void addEvents(List<Event> events, TicketRepository ticketRepository);

    Boolean isEmpty();
}
