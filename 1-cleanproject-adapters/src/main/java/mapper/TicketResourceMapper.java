package mapper;

import businessObjects.OneWayTicket;
import businessObjects.Ticket;
import resources.TicketResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TicketResourceMapper {

    public TicketResource map(final Ticket ticket) throws Exception {
        if (Objects.isNull(ticket)) {
            return null;
        }
        return new TicketResource(ticket.getId(), ticket.getEventID(), String.valueOf(ticket.getPrice()), ticket.getSeat(), ticket.isBooked(), ticket.getValidationCode(), ticket.getEndOfValidity());

    }

    public List<TicketResource> map(List<Ticket> tickets) throws Exception {
        if ((Objects.isNull(tickets )) || (tickets.isEmpty())) {
            return Collections.emptyList();
        }
        List<TicketResource> ticketResources = new ArrayList<>();
        for (Ticket ticket : tickets) {
            ticketResources.add(map(ticket));
        }

        return ticketResources;

    }
}
