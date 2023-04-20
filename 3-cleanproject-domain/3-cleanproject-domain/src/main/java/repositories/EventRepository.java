package repositories;

import businessObjects.Event;

import java.util.ArrayList;
import java.util.List;

public interface EventRepository {
        List<Event> findAllEvents();
        Event findEventById(String id);
        void addEvent(Event event);
        void addEvents(ArrayList<Event> events);
        Boolean isEmpty();
}
