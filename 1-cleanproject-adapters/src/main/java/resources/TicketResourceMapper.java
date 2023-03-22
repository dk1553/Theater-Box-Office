package resources;

import businessObjects.Event;
import businessObjects.Ticket;

import java.util.ArrayList;

public class TicketResourceMapper {

    public TicketResource map(final Ticket ticket) throws Exception {
        return  new TicketResource(ticket.getId(), ticket.getEventID(), String.valueOf(ticket.getPrice()), ticket.getSeat().getSeatID(), ticket.isBooked());

    }

    public ArrayList<TicketResource> map(ArrayList<Ticket> tickets) throws Exception {
        ArrayList<TicketResource> ticketResources=new ArrayList<>();
        for (Ticket ticket:tickets){
            ticketResources.add(map(ticket));
        }
        return ticketResources;
    }
}
