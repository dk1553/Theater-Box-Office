package persistence;

import businessObjects.Event;
import businessObjects.Performance;
import businessObjects.TheaterBuilding;
import db.JDBCService;
import repositories.EventRepository;

import java.util.ArrayList;

public class EventRepositoryJDBC implements EventRepository {
    public EventRepositoryJDBC(TheaterBuilding theaterBuilding){
        eventList= new ArrayList<>();
        loadTheaterProgramFromDB(theaterBuilding);
    }



    private ArrayList <Event> eventList;



    @Override
    public ArrayList<Event> findAllEvents() {
        return eventList;
    }

    @Override
    public Performance findEventById(int id) {
        return null;
    }

    @Override
    public void deleteEventById(int id) {

    }

    @Override
    public void addEvent(Event event) {
        eventList.add(event);
    }

    @Override
    public void addEvents(ArrayList<Event> events) {
        try {
            JDBCService jdbcService = new JDBCService();
            jdbcService.addEventsToDatabase(events);
            jdbcService.close();
            this.eventList.addAll(events);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTheaterProgramFromDB(TheaterBuilding theaterBuilding) {
        try {
            eventList=new ArrayList<>();
            JDBCService jdbcService = new JDBCService();
            ArrayList <Event> eventsFormDB=jdbcService.getTheaterProgram(theaterBuilding);
            if (!eventsFormDB.isEmpty()){
                eventList.addAll(eventsFormDB);
            }
            jdbcService.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

}








}



