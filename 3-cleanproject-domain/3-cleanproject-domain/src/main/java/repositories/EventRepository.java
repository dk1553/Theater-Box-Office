package repositories;

import businessObjects.Event;

import java.util.List;

public interface EventRepository {
    List<Event> findAllEvents();

    Event findEventById(String id);

    void addEvent(Event event);

    void addEvents(List<Event> events);

    Boolean isEmpty();
}
