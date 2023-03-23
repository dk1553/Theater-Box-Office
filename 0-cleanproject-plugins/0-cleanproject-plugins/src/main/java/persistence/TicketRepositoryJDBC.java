package persistence;

import businessObjects.Event;
import businessObjects.TheaterBuilding;
import businessObjects.Ticket;
import db.JDBCService;
import repositories.TicketRepository;
import resources.TicketMapper;
import mapper.TicketResourceMapper;

import java.sql.SQLException;
import java.util.ArrayList;

public class TicketRepositoryJDBC implements TicketRepository {
    private ArrayList <Ticket> ticketList;
    public TicketRepositoryJDBC(TheaterBuilding theaterBuilding) {
        this.ticketList = new ArrayList<>();
        loadTicketsFromDB(theaterBuilding);
    }

    @Override
    public ArrayList<Ticket> findTicketsOfEvent(String eventID){
        ArrayList<Ticket> ticketsOfEvent= new ArrayList<>();
        for (Ticket ticket:this.ticketList){
            if (ticket.getEventID().equalsIgnoreCase(eventID)){
                ticketsOfEvent.add(ticket);
            }
        }
        return ticketsOfEvent;
    }

    @Override
    public Ticket findTicketById(String id){
        for (Ticket ticket:this.ticketList){
            if (ticket.getId().equalsIgnoreCase(id)){
                return ticket;
            }
        }
        return null;
    }
    @Override
    public Ticket buy(Ticket ticket) {
            if (ticket!=null){
                if (!ticket.isBooked()){
                    try {
                        JDBCService jdbcService = new JDBCService();
                        TicketResourceMapper ticketResourceMapper= new TicketResourceMapper();
                        jdbcService.buyTicket(ticketResourceMapper.map(ticket));
                        jdbcService.close();
                        ticketList.get(ticketList.indexOf(ticket)).setBooked();
                        return ticket;
                    } catch (SQLException | ClassNotFoundException e) {
                        return null;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                return null;
            }
        return null;
    }

    @Override
    public ArrayList<Ticket> findAllTickets() {
        return ticketList;
    }
    @Override
    public void addTickets(ArrayList<Event> events) {
        try {
            for (Event e:events){
                JDBCService jdbcService = new JDBCService();
                TicketResourceMapper ticketResourceMapper= new TicketResourceMapper();
                jdbcService.addTicketsToDatabase(ticketResourceMapper.map(e.getTickets()));
                jdbcService.close();
             //   System.out.println("tetetet...."+ticketResourceMapper.map(e.getTickets()).size());
                System.out.println("tetetet...."+e.getTickets().size());
                this.ticketList.addAll(e.getTickets());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
}
    private void loadTicketsFromDB(TheaterBuilding theaterBuilding)  {
        try {
            TicketMapper ticketMapper= new TicketMapper();
            ticketList=new ArrayList<>();
            JDBCService jdbcService = new JDBCService();
            ArrayList <Ticket> ticketsFormDB=ticketMapper.map(jdbcService.getTickets(), theaterBuilding);
            if (!ticketsFormDB.isEmpty()){
                ticketList.addAll(ticketsFormDB);
            }
            jdbcService.close();
            System.out.println("tttt---"+ticketList.size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
