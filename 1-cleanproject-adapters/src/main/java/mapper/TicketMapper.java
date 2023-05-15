package mapper;

import businessObjects.*;
import repositories.SeatRepository;
import resources.TicketResource;

import java.text.SimpleDateFormat;
import java.util.*;

public class TicketMapper {
    public Ticket map(final TicketResource ticketResource, SeatRepository seatRepository) throws Exception {
        if (Objects.isNull(ticketResource )||Objects.isNull(seatRepository )) {
            return null;
        }
        try {
            if (ticketResource.getEventID().equals("")|| (Objects.isNull(ticketResource.getEventID()))){
                SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.yyyy");
                System.out.println(ticketResource.getEndOfValidity());
                Date endOfValidity = formatterDate.parse(ticketResource.getEndOfValidity());
                return new YearTicket(ticketResource.getId(), new Price(ticketResource.getPrice()), ticketResource.isBooked(), ticketResource.getValidationCode(), endOfValidity);
            }
            return new OneWayTicket(ticketResource.getId(), ticketResource.getEventID(), new Price(ticketResource.getPrice()), seatRepository.findSeatByID(ticketResource.getSeat()), ticketResource.isBooked(), ticketResource.getValidationCode());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<Ticket> map(List<TicketResource> ticketResources, SeatRepository seatRepository) throws Exception {
        if (Objects.isNull(ticketResources )) {
            return Collections.emptyList();
        }
        List<Ticket> tickets = new ArrayList<>();
        for (TicketResource ticketResource : ticketResources) {
            tickets.add(map(ticketResource, seatRepository));
        }
        return tickets;

    }

}
