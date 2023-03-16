package persistence;

import businessObjects.Event;
import businessObjects.Performance;
import businessObjects.TheaterBuilding;
import businessObjects.Ticket;
import db.DBManager;
import repositories.EventRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class TheaterProgram implements EventRepository {
    public  TheaterProgram(){
        eventList= new ArrayList<>();
        ticketList= new ArrayList<>();
        try {
          //  loadTheaterProgramFromDB();
           // loadTicketsFromDB();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void loadTicketsFromDB(TheaterBuilding theaterBuilding) throws Exception {

        ticketList=new ArrayList<>();
        DBManager dbManagerTicket = new DBManager();
        ArrayList <Ticket> ticketsFormDB=dbManagerTicket.getTickets(theaterBuilding);
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
@Override
    public void loadTheaterProgramFromDB(TheaterBuilding theaterBuilding) {

        try {
            eventList=new ArrayList<>();
            DBManager dbManagerProgram = new DBManager();
            ArrayList <Event> eventsFormDB=dbManagerProgram.getTheaterProgram();
            if (!eventsFormDB.isEmpty()){
                eventList.addAll(eventsFormDB);
            }
            dbManagerProgram.close();

            loadTicketsFromDB(theaterBuilding);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

}

    @Override
    public ArrayList<Ticket> findAllTickets() {
        return  ticketList;
    }

    private Ticket findTicketById(String id){
        for (Ticket ticket:this.ticketList){
            if (ticket.getId().equalsIgnoreCase(id)){
                return ticket;
            }
        }
        return null;

    }

    @Override
    public Ticket buyTicket(String ticketID) {
        for (Ticket ticket:ticketList){
            if (ticket.getId().equalsIgnoreCase(ticketID)){
                if (!ticket.isBooked()){
                    try {
                        DBManager dbManagerTicket = new DBManager();
                        dbManagerTicket.buyTicket(ticket);
                        dbManagerTicket.close();
                        ticketList.get(ticketList.indexOf(ticket)).setBooked();
                        return ticket;
                    } catch (SQLException | ClassNotFoundException e) {
                        return null;
                    }
                }
                return null;
            }
        }
        return null;

    }


}



