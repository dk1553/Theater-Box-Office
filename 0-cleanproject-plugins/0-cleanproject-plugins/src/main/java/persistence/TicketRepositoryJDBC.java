package persistence;

import businessObjects.Event;
import businessObjects.Ticket;
import db.JDBCService;
import repositories.SeatRepository;
import repositories.TicketRepository;
import mapper.TicketMapper;
import mapper.TicketResourceMapper;
import java.util.ArrayList;
import java.util.List;


public class TicketRepositoryJDBC implements TicketRepository {
    private List<Ticket> ticketList;

    public TicketRepositoryJDBC(SeatRepository seatRepository) {
        this.ticketList = new ArrayList<>();
        loadTicketsFromDB(seatRepository);
    }

    @Override
    public List<Ticket> findTicketsOfEvent(String eventID) {
        ArrayList<Ticket> ticketsOfEvent = new ArrayList<>();
        for (Ticket ticket : this.ticketList) {
            if (ticket.getEventID().equalsIgnoreCase(eventID)) {
                ticketsOfEvent.add(ticket);
            }
        }
        return ticketsOfEvent;
    }

    @Override
    public Ticket findTicketById(String id) {
        for (Ticket ticket : this.ticketList) {
            if (ticket.getId().equalsIgnoreCase(id)) {
                return ticket;
            }
        }
        return null;
    }

    @Override
    public void save(Ticket ticket) throws Exception {
        JDBCService jdbcService = new JDBCService();
        TicketResourceMapper ticketResourceMapper = new TicketResourceMapper();
        jdbcService.buyTicket(ticketResourceMapper.map(ticket));
        jdbcService.close();
    }

    @Override
    public List<Ticket> findAllTickets() {
        return ticketList;
    }

    @Override
    public void addTickets(ArrayList<Event> events) {
        try {
            for (Event event : events) {

                JDBCService jdbcService = new JDBCService();
                TicketResourceMapper ticketResourceMapper = new TicketResourceMapper();
                jdbcService.addTicketsToDatabase(ticketResourceMapper.map(event.getTickets()));
                jdbcService.close();
                this.ticketList.addAll(event.getTickets());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTicketsFromDB(SeatRepository seatRepository) {
        try {
            TicketMapper ticketMapper = new TicketMapper();
            ticketList = new ArrayList<>();
            JDBCService jdbcService = new JDBCService();
            ArrayList<Ticket> ticketsFormDB = ticketMapper.map(jdbcService.getTickets(), seatRepository);
            jdbcService.close();
            if ((ticketsFormDB != null) && (!ticketsFormDB.isEmpty())) {
                ticketList.addAll(ticketsFormDB);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Boolean isEmpty() {
        return (ticketList == null) || (ticketList.isEmpty());
    }
}
