package persistence;

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
    public EventRepositoryJDBC(HallRepository hallRepository, TicketRepository ticketRepository, PerformanceRepository performanceRepository){
        eventList= new ArrayList<>();
        loadTheaterProgramFromDB(hallRepository,performanceRepository, ticketRepository);
    }



    private List<Event> eventList;



    @Override
    public List<Event> findAllEvents() {
        return eventList;
    }

    @Override
    public Event findEventById(String eventID) {
        for (Event event:eventList){
            if (event.getId().equalsIgnoreCase(eventID)){
                return event;
            }
        }
        return null;
    }


    @Override
    public void addEvent(Event event) {
        eventList.add(event);
    }

    @Override
    public void addEvents(List<Event> events) {
        try {
            JDBCService jdbcService = new JDBCService();
            EventResourceMapper eventResourceMapper= new EventResourceMapper();
            jdbcService.addEventsToDatabase(eventResourceMapper.map(events));
            jdbcService.close();
            this.eventList.addAll(events);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Boolean isEmpty(){
        if ((!Objects.isNull(eventList))&&(!eventList.isEmpty())){
            return  false;
        }else {
            return true;
        }
    }



        private void loadTheaterProgramFromDB(HallRepository hallRepository, PerformanceRepository performanceRepository, TicketRepository ticketRepository) {
            EventMapper eventMapper= new EventMapper();
            try {
                eventList=new ArrayList<>();
                JDBCService jdbcService = new JDBCService();
               List <Event> eventsFormDB=eventMapper.map(jdbcService.getTheaterProgram(), performanceRepository, hallRepository, ticketRepository);
                if ((!Objects.isNull(eventsFormDB)) && (!eventsFormDB.isEmpty())){
                    eventList.addAll(eventsFormDB);
                }
                jdbcService.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

}








}



