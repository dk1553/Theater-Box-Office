package services;

import businessObjects.Price;
import businessObjects.Ticket;
import businessObjects.YearTicket;
import repositories.TicketRepository;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookYearTicketDomainServiceJDBC implements BookYearTicketDomainService {
    @Override
    public Ticket bookTicket(TicketRepository ticketRepository) throws Exception {

        YearTicket ticket = new YearTicket(new Price(BigDecimal.valueOf(500)));
        try {
            ticket.setBooked();
            List<Ticket> tickets= new ArrayList<>();
            tickets.add(ticket);
            ticketRepository.addTickets( tickets);
            return ticket;
        } catch (Exception e) {
            return null;
        }
    }
}
