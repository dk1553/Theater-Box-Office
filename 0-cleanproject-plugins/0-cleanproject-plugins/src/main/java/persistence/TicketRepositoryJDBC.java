package persistence;

import businessObjects.Event;
import businessObjects.TheaterBuilding;
import businessObjects.Ticket;
import db.JDBCService;
import repositories.TicketRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class TicketRepositoryJDBC implements TicketRepository {
    private ArrayList <Ticket> ticketList;
    private Ticket findTicketById(String id){
        for (Ticket ticket:this.ticketList){
            if (ticket.getId().equalsIgnoreCase(id)){
                return ticket;
            }
        }
        return null;

    }

    public TicketRepositoryJDBC(TheaterBuilding theaterBuilding) {
        this.ticketList = new ArrayList<>();
        loadTicketsFromDB(theaterBuilding);
    }

    @Override
    public Ticket buyTicket(String ticketID) {
        for (Ticket ticket:ticketList){
            if (ticket.getId().equalsIgnoreCase(ticketID)){
                if (!ticket.isBooked()){
                    try {
                        JDBCService jdbcService = new JDBCService();
                        jdbcService.buyTicket(ticket);
                        jdbcService.close();
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

    private void loadTicketsFromDB(TheaterBuilding theaterBuilding)  {
        try {
            ticketList=new ArrayList<>();
            JDBCService jdbcService = new JDBCService();
            ArrayList <Ticket> ticketsFormDB=jdbcService.getTickets(theaterBuilding);
            if (!ticketsFormDB.isEmpty()){
                ticketList.addAll(ticketsFormDB);
            }
            jdbcService.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addTickets(ArrayList<Event> events) {
        try {
            for (Event e:events){
                JDBCService jdbcService = new JDBCService();
                jdbcService.addTicketsToDatabase(e.getTickets(), e.getBasicPrice(),e.getId());
                jdbcService.close();
                this.ticketList.addAll(e.getTickets());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
}
}
