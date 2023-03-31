package mapper;

import businessObjects.Ticket;
import resources.TicketResource;

import java.util.ArrayList;

public class TicketResourceMapper {

    public TicketResource map(final Ticket ticket) throws Exception {
        if (ticket!=null){
             return  new TicketResource(ticket.getId(), ticket.getEventID(), String.valueOf(ticket.getPrice()), ticket.getSeat().getSeatID(), ticket.isBooked(), ticket.getValidationCode());
        }else {
            return null;
        }
    }

    public ArrayList<TicketResource> map(ArrayList<Ticket> tickets) throws Exception {
        ArrayList<TicketResource> ticketResources=new ArrayList<>();
        if ((tickets!=null)&&(!tickets.isEmpty())){

            for (Ticket ticket:tickets){
                ticketResources.add(map(ticket));
            }
            return ticketResources;
        }else{
            return ticketResources;
    }
}}
