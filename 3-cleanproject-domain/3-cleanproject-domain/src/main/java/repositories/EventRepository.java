package repositories;

import businessObjects.Event;

import java.util.ArrayList;

public interface EventRepository {
        ArrayList<Event> findAllEvents();
        Event findEventById(String id);
        void addEvent(Event event);
        void addEvents(ArrayList<Event> events);
        Boolean isEmpty();
}
