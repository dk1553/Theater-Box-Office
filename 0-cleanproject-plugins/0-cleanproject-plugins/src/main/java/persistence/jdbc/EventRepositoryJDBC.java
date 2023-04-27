package persistence.jdbc;

import businessObjects.Event;
import db.JDBCService;
import repositories.EventRepository;
import repositories.HallRepository;
import repositories.PerformanceRepository;
import repositories.TicketRepository;
import mapper.EventMapper;
import mapper.EventResourceMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventRepositoryJDBC implements EventRepository {
    public EventRepositoryJDBC(HallRepository hallRepository, TicketRepository ticketRepository, PerformanceRepository performanceRepository) {
        eventList = new ArrayList<>();
        loadTheaterProgramFromDB(hallRepository, performanceRepository, ticketRepository);
    }


    private List<Event> eventList;


    @Override
    public List<Event> findAllEvents() {
        return eventList;
    }

    @Override
    public Event findEventById(String eventID) {
        for (Event event : eventList) {
            if (event.getId().equalsIgnoreCase(eventID)) {
                return event;
            }
        }
        return null;
    }


    @Override
    public void addEvents(List<Event> events, TicketRepository ticketRepository) {
        try {
            EventResourceMapper eventResourceMapper = new EventResourceMapper();
            JDBCService.addEventsToDatabase(eventResourceMapper.map(events,ticketRepository ));

            this.eventList.addAll(events);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean isEmpty() {
        return (Objects.isNull(eventList)) || (eventList.isEmpty());
    }


    private void loadTheaterProgramFromDB(HallRepository hallRepository, PerformanceRepository performanceRepository, TicketRepository ticketRepository) {
        EventMapper eventMapper = new EventMapper();
        try {
            eventList = new ArrayList<>();
            List<Event> eventsFormDB = eventMapper.map(JDBCService.getTheaterProgram(), performanceRepository, hallRepository);
            if ((!Objects.isNull(eventsFormDB)) && (!eventsFormDB.isEmpty())) {
                eventList.addAll(eventsFormDB);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}



