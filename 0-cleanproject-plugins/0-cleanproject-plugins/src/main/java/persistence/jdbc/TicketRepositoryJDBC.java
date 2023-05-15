package persistence.jdbc;

import businessObjects.OneWayTicket;
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
        List<Ticket> ticketsOfEvent = new ArrayList<>();
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
        TicketResourceMapper ticketResourceMapper = new TicketResourceMapper();
        JDBCService.buyTicket(ticketResourceMapper.map(ticket));

    }

    @Override
    public List<Ticket> findAllTickets() {
        return ticketList;
    }

    @Override
    public void addTickets(List<Ticket> tickets) {
        try {
                TicketResourceMapper ticketResourceMapper = new TicketResourceMapper();
                 JDBCService.addTicketsToDatabase(ticketResourceMapper.map(tickets));
                this.ticketList.addAll(tickets);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTicketsFromDB(SeatRepository seatRepository) {
        try {
            TicketMapper ticketMapper = new TicketMapper();
            ticketList = new ArrayList<>();
            List<Ticket> ticketsFormDB = ticketMapper.map(JDBCService.getTickets(), seatRepository);
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
