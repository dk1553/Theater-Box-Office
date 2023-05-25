package repositories;

import entities.Event;

import java.util.List;

public interface EventRepository {
    List<Event> findAllEvents();

    Event findEventById(String id);

    void addEvents(List<Event> events, TicketRepository ticketRepository);

    void loadTheaterProgramFromDB(HallRepository hallRepository, PerformanceRepository performanceRepository);
}
