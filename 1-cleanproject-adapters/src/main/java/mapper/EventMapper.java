package mapper;

import businessObjects.*;
import repositories.PerformanceRepository;
import repositories.TicketRepository;
import resources.EventResource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class EventMapper  {

    public Event map(final EventResource eventResource, PerformanceRepository performanceRepository, TheaterBuilding theaterBuilding, TicketRepository ticketRepository) throws Exception {
        try {
            String eventID=eventResource.getEventID();
            ArrayList<Ticket> tickets = new ArrayList<>(ticketRepository.findTicketsOfEvent(eventID));
            return buildEvent( eventResource,  performanceRepository,  theaterBuilding, tickets);
        } catch (Exception e) {
            return null;
        }
    }
    public ArrayList<Event> map(ArrayList<EventResource> eventResources, PerformanceRepository performanceRepository, TheaterBuilding theaterBuilding, TicketRepository ticketRepository) throws Exception {
        if ((eventResources!=null)&&(!eventResources.isEmpty())){
            ArrayList<Event> events=new ArrayList<>();
            for (EventResource eventResource:eventResources){
                events.add(map(eventResource, performanceRepository,theaterBuilding, ticketRepository));
            }
            return events;
        }else {
            return null;
        }
    }
    public ArrayList<Event> mapNewObject(ArrayList<EventResource> eventResources, PerformanceRepository performanceRepository, TheaterBuilding theaterBuilding, TicketRepository ticketRepository) {
        if ((eventResources!=null)&&(!eventResources.isEmpty())){
        ArrayList<Event> events=new ArrayList<>();
        for (EventResource eventResource:eventResources){
            events.add(mapNewObject(eventResource, performanceRepository,theaterBuilding));
        }
        return events;
        }else {
            return null;
        }
    }

    public Event mapNewObject(EventResource eventResource, PerformanceRepository performanceRepository, TheaterBuilding theaterBuilding){
        try {
            Hall hall=theaterBuilding.findHallByName(eventResource.getHall());
            Price price= new Price(eventResource.getPrice());
            ArrayList <Ticket> tickets=new ArrayList<>();
            String eventID=eventResource.getEventID();
            for (Seat seat: Objects.requireNonNull(hall).getSeats()){
                tickets.add(new Ticket(eventID,price, seat));
            }
            return buildEvent( eventResource,  performanceRepository,  theaterBuilding, tickets);

        } catch (Exception e) {
            return null;
        }

    }


private Event buildEvent(EventResource eventResource, PerformanceRepository performanceRepository, TheaterBuilding theaterBuilding, ArrayList <Ticket> tickets){
   try {
       Performance performance=performanceRepository.findPerformanceByName(eventResource.getPerformance());
       Hall hall=theaterBuilding.findHallByName(eventResource.getHall());
       Price price= new Price(eventResource.getPrice());
       SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.yyyy");
       Date date = formatterDate.parse(eventResource.getDate());
       SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm");
       Date time = formatterTime.parse(eventResource.getTime());
       String eventID=eventResource.getEventID();
       if ((performance!=null)&&(date!=null)&&(time!=null)&&(hall!=null)){
           return new Event(eventID,performance, date,time, hall,price, tickets);
       }else {
           return null;
       }
   } catch (Exception e) {
       return null;
   }

}

}
