package mapper;

import businessObjects.*;
import repositories.SeatRepository;
import resources.TicketResource;

import java.util.ArrayList;
import java.util.List;

public class TicketMapper {
    public Ticket map(final TicketResource ticketResource, SeatRepository seatRepository) throws Exception {
        try {
            return new Ticket(ticketResource.getId(), ticketResource.getEventID(), new Price(ticketResource.getPrice()), seatRepository.findSeatByID(ticketResource.getSeat()), ticketResource.isBooked(), ticketResource.getValidationCode());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<Ticket> map(List<TicketResource> ticketResources, SeatRepository seatRepository) throws Exception {
        if (ticketResources == null) {
            return null;
        }
        List<Ticket> tickets = new ArrayList<>();
        for (TicketResource ticketResource : ticketResources) {
            tickets.add(map(ticketResource, seatRepository));
        }
        return tickets;

    }

}
