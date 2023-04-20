package mapper;

import businessObjects.Ticket;
import resources.TicketResource;

import java.util.ArrayList;
import java.util.List;

public class TicketResourceMapper {

    public TicketResource map(final Ticket ticket) throws Exception {
        if (ticket != null) {
            return new TicketResource(ticket.getId(), ticket.getEventID(), String.valueOf(ticket.getPrice()), ticket.getSeat(), ticket.isBooked(), ticket.getValidationCode());
        } else {
            return null;
        }
    }

    public List<TicketResource> map(List<Ticket> tickets) throws Exception {
        List<TicketResource> ticketResources = new ArrayList<>();
        if ((tickets != null) && (!tickets.isEmpty())) {

            for (Ticket ticket : tickets) {
                ticketResources.add(map(ticket));
            }
            return ticketResources;
        } else {
            return ticketResources;
        }
    }
}
