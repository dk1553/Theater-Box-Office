package mapper;

import businessObjects.*;
import resources.TicketResource;

import java.util.ArrayList;
import java.util.Collection;

public class TicketMapper {
    public Ticket map(final TicketResource ticketResource, TheaterBuilding theaterBuilding) throws Exception {
        try {
            return new Ticket(ticketResource.getId(), ticketResource.getEventID(),new Price(ticketResource.getPrice()), theaterBuilding.findSeatByName(ticketResource.getSeat()), ticketResource.isBooked(), ticketResource.getValidationCode());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Ticket> map(ArrayList<TicketResource> ticketResources, TheaterBuilding theaterBuilding) throws Exception {
        ArrayList<Ticket> tickets=new ArrayList<>();
        if (ticketResources!=null){
            for (TicketResource ticketResource:ticketResources){
                tickets.add(map(ticketResource, theaterBuilding));
            }
            return tickets;
        }else{
            return null;
        }

    }

}
