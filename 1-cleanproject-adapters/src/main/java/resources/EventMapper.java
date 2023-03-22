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

      //  tickets.addAll(ticketRepository.findTicketsOfEvent(eventID));
        TicketMapper ticketMapper= new TicketMapper();
        ArrayList <TicketResource> ticketResources=new ArrayList<>();
        ticketResources=eventResource.getTicketResources();
        ArrayList <Ticket> tickets= new ArrayList<>();
        if( (ticketResources.size()>0)&&(tickets!=null)){
            System.out.println("hier---ticketResources------------"+ticketResources.size());
            // if ((!ticketResources.isEmpty()&&(ticketResources!=null))){

            ArrayList <Ticket> ticketsFormMap= new ArrayList<>();

            ticketsFormMap=ticketMapper.map(ticketResources, theaterBuilding);
            System.out.println("hier---we------------"+ticketsFormMap.size());
            for (Ticket ticket:ticketsFormMap){
                tickets.add(ticket);
            }
        }


      //  }




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
}
