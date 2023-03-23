package mapper;

import businessObjects.*;
import repositories.PerformanceRepository;
import repositories.TicketRepository;
import resources.EventResource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        ArrayList <Ticket> tickets=new ArrayList<>();
        tickets.addAll(ticketRepository.findTicketsOfEvent(eventID));

        if ((performance!=null)&&(date!=null)&&(time!=null)&&(hall!=null)){
            return new Event(eventID,performance, date,time, hall,price, tickets);
        }
        System.out.println("ups");
        return null;

    }

    public ArrayList<Event> map(ArrayList<EventResource> eventResources, PerformanceRepository performanceRepository, TheaterBuilding theaterBuilding, TicketRepository ticketRepository) throws Exception {
        ArrayList<Event> events=new ArrayList<>();
        for (EventResource eventResource:eventResources){
            events.add(map(eventResource, performanceRepository,theaterBuilding, ticketRepository));
        }
        return events;
    }

    public ArrayList<Event> mapNewObject(ArrayList<EventResource> eventResources, PerformanceRepository performanceRepository, TheaterBuilding theaterBuilding, TicketRepository ticketRepository) throws Exception {
        ArrayList<Event> events=new ArrayList<>();
        for (EventResource eventResource:eventResources){
            events.add(mapNewObject(eventResource, performanceRepository,theaterBuilding, ticketRepository));
        }
        return events;
    }

    public Event mapNewObject(final EventResource eventResource, PerformanceRepository performanceRepository, TheaterBuilding theaterBuilding, TicketRepository ticketRepository) throws Exception {

        Performance performance=performanceRepository.findPerformanceByName(eventResource.getPerformance());
        Hall hall=theaterBuilding.findHallByName(eventResource.getHall());
        Price price= new Price(eventResource.getPrice());
        SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.yyyy");
        Date date = formatterDate.parse(eventResource.getDate());
        SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm");
        Date time = formatterTime.parse(eventResource.getTime());
        String eventID=eventResource.getEventID();

        ArrayList <Ticket> tickets=new ArrayList<>();
        for (Seat seat:hall.getSeats()){
            tickets.add(new Ticket(eventID,price, seat));
        }
        if ((performance!=null)&&(date!=null)&&(time!=null)&&(hall!=null)){
            return new Event(eventID,performance, date,time, hall,price, tickets);
        }
        return null;

    }


}
