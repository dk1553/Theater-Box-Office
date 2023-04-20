package mapper;

import businessObjects.*;
import repositories.SeatRepository;
import resources.TicketResource;

import java.util.ArrayList;

public class TicketMapper {
    public Ticket map(final TicketResource ticketResource, SeatRepository seatRepository) throws Exception {
        try {
            return new Ticket(ticketResource.getId(), ticketResource.getEventID(),new Price(ticketResource.getPrice()), seatRepository.findSeatByID(ticketResource.getSeat()), ticketResource.isBooked(), ticketResource.getValidationCode());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Ticket> map(ArrayList<TicketResource> ticketResources, SeatRepository seatRepository) throws Exception {
        ArrayList<Ticket> tickets=new ArrayList<>();
        if (ticketResources!=null){
            for (TicketResource ticketResource:ticketResources){
                tickets.add(map(ticketResource, seatRepository));
            }
            return tickets;
        }else{
            return null;
        }

    }

}
