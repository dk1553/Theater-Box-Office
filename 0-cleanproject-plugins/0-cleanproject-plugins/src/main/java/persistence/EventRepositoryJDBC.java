package persistence;

import businessObjects.Event;
import businessObjects.TheaterBuilding;
import db.JDBCService;
import repositories.EventRepository;
import repositories.PerformanceRepository;
import repositories.TicketRepository;
import mapper.EventMapper;
import mapper.EventResourceMapper;

import java.util.ArrayList;

public class EventRepositoryJDBC implements EventRepository {
    public EventRepositoryJDBC(TheaterBuilding theaterBuilding, TicketRepository ticketRepository, PerformanceRepository performanceRepository){
        eventList= new ArrayList<>();
        loadTheaterProgramFromDB(theaterBuilding,performanceRepository, ticketRepository);
    }



    private ArrayList <Event> eventList;



    @Override
    public ArrayList<Event> findAllEvents() {
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
    public void addEvents(ArrayList<Event> events) {
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



        private void loadTheaterProgramFromDB(TheaterBuilding theaterBuilding, PerformanceRepository performanceRepository, TicketRepository ticketRepository) {
            EventMapper eventMapper= new EventMapper();
            try {
                eventList=new ArrayList<>();
                JDBCService jdbcService = new JDBCService();
                ArrayList <Event> eventsFormDB=eventMapper.map(jdbcService.getTheaterProgram(), performanceRepository, theaterBuilding, ticketRepository);
                if (!eventsFormDB.isEmpty()){
                    eventList.addAll(eventsFormDB);
                }
                jdbcService.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

}








}



