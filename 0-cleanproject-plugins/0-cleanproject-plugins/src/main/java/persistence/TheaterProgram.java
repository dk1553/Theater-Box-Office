package persistence;

import businessObjects.Event;
import businessObjects.Performance;
import businessObjects.TheaterBuilding;
import db.DBManager;
import repositories.EventRepository;

import java.util.ArrayList;

public class TheaterProgram implements EventRepository {
    public  TheaterProgram(){
        eventList= new ArrayList<>();


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
            DBManager dbManagerEvent = new DBManager();
            dbManagerEvent.addEventsToDatabase(events);
            dbManagerEvent.close();
            this.eventList.addAll(events);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
@Override
    public void loadTheaterProgramFromDB(TheaterBuilding theaterBuilding) {

        try {
            eventList=new ArrayList<>();
            DBManager dbManagerProgram = new DBManager();
            ArrayList <Event> eventsFormDB=dbManagerProgram.getTheaterProgram(theaterBuilding);
            if (!eventsFormDB.isEmpty()){
                eventList.addAll(eventsFormDB);
            }
            dbManagerProgram.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

}








}



