package resources;

import businessObjects.*;

import java.util.ArrayList;

public class TicketMapper {
    public Ticket map(final TicketResource ticketResource, TheaterBuilding theaterBuilding) throws Exception {
        return new Ticket(ticketResource.getId(), ticketResource.getEventID(),new Price(ticketResource.getPrice()), theaterBuilding.findSeatByName(ticketResource.getSeat()), ticketResource.isBooked());

    }

    public ArrayList<Ticket> map(ArrayList<TicketResource> ticketResources, TheaterBuilding theaterBuilding) throws Exception {
        ArrayList<Ticket> tickets=new ArrayList<>();
        for (TicketResource ticketResource:ticketResources){
            tickets.add(map(ticketResource, theaterBuilding));
        }
        return tickets;
    }
}
