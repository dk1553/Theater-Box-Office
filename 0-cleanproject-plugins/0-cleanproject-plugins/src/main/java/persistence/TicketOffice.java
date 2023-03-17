package persistence;

import businessObjects.Event;
import businessObjects.TheaterBuilding;
import businessObjects.Ticket;
import db.DBManager;
import repositories.TicketRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class TicketOffice implements TicketRepository {
    private ArrayList <Ticket> ticketList;
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

    @Override
    public ArrayList<Ticket> findAllTickets() {
        return ticketList;
    }

    @Override
    public void loadTicketsFromDB(TheaterBuilding theaterBuilding) throws Exception {
        ticketList=new ArrayList<>();
        DBManager dbManagerTicket = new DBManager();
        ArrayList <Ticket> ticketsFormDB=dbManagerTicket.getTickets(theaterBuilding);
        if (!ticketsFormDB.isEmpty()){
            ticketList.addAll(ticketsFormDB);
        }
        dbManagerTicket.close();
    }

    @Override
    public void addTickets(ArrayList<Event> events) {
        try {

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
}
