package persistence;

import businessObjects.Event;
import businessObjects.Performance;
import db.DBManager;
import repositories.EventRepository;
import resources.EventResource;

import java.sql.SQLException;
import java.util.ArrayList;

public class TheaterProgram implements EventRepository {
    public  TheaterProgram(){
        eventList= new ArrayList<>();
        try {
            loadTheaterProgramFromDB();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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
        eventList.addAll(events);
    }

    public void loadTheaterProgramFromDB() throws Exception {
        DBManager dbManagerProgram = new DBManager();
        eventList.addAll(dbManagerProgram.getTheaterProgram());
        dbManagerProgram.close();
    }


}



