package persistence;

import businessObjects.Event;
import businessObjects.Performance;
import businessObjects.Ticket;
import db.DBManager;
import repositories.EventRepository;
import resources.EventResource;

import java.sql.SQLException;
import java.util.ArrayList;

public class TheaterProgram implements EventRepository {
    public  TheaterProgram(){
        eventList= new ArrayList<>();
        ticketList= new ArrayList<>();
        try {
            loadTheaterProgramFromDB();
          //  loadTicketsFromDB();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void loadTicketsFromDB() throws Exception {

        ticketList=new ArrayList<>();
        DBManager dbManagerTicket = new DBManager();
        ArrayList <Ticket> ticketsFormDB=dbManagerTicket.getTickets();
        if (!ticketsFormDB.isEmpty()){
            ticketList.addAll(ticketsFormDB);
        }
        dbManagerTicket.close();
    }

    private ArrayList <Event> eventList;
    private ArrayList <Ticket> ticketList;

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


            for (Event e:events){
                DBManager dbManagerTickets = new DBManager();
                dbManagerTickets.addTicketsToDatabase(e.getTickets(), e.getBasicPrice(),e.getId());
                dbManagerTickets.close();
                this.ticketList.addAll(e.getTickets());
            }




        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTheaterProgramFromDB() throws Exception {
        eventList=new ArrayList<>();
        DBManager dbManagerProgram = new DBManager();
        ArrayList <Event> eventsFormDB=dbManagerProgram.getTheaterProgram();
        if (!eventsFormDB.isEmpty()){
            eventList.addAll(eventsFormDB);
        }
        dbManagerProgram.close();
    }


}



