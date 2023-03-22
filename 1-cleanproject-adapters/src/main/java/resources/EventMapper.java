package resources;

import Service.TheaterService;
import businessObjects.*;
import repositories.PerformanceRepository;
import repositories.TicketRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Function;

public class EventMapper  {

    public Event map(final EventResource eventResource, PerformanceRepository performanceRepository, TheaterBuilding theaterBuilding, TicketRepository ticketRepository) throws Exception {

        Performance performance=performanceRepository.findPerformanceByName(eventResource.getPerformance());
        Hall hall=theaterBuilding.findHallByName(eventResource.getHall());
        Price price= new Price(eventResource.getPrice());
        SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.yyyy");
        Date date = formatterDate.parse(eventResource.getDate());
        SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm");
        Date time = formatterTime.parse(eventResource.getTime());
        String eventID=eventResource.getEventID();
        ArrayList <Ticket> tickets= ticketRepository.findTicketsOfEvent(eventID);

        if ((performance!=null)&&(date!=null)&&(time!=null)&&(hall!=null)){
            return new Event(eventID,performance, date,time, hall,price, tickets);
        }
        return null;

    }

    public ArrayList<Event> map(ArrayList<EventResource> eventResources, PerformanceRepository performanceRepository, TheaterBuilding theaterBuilding, TicketRepository ticketRepository) throws Exception {
        ArrayList<Event> events=new ArrayList<>();
        for (EventResource eventResource:eventResources){
            events.add(map(eventResource, performanceRepository,theaterBuilding, ticketRepository));
        }
        return events;
    }
}
