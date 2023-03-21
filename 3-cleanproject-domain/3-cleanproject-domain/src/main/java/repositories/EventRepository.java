package repositories;

import businessObjects.Event;
import businessObjects.Performance;
import businessObjects.TheaterBuilding;
import businessObjects.Ticket;

import java.util.ArrayList;

public interface EventRepository {
        ArrayList<Event> findAllEvents();
        Performance findEventById(int id);
        void deleteEventById(int id);
        void addEvent(Event event);
        void addEvents(ArrayList<Event> events);
}
