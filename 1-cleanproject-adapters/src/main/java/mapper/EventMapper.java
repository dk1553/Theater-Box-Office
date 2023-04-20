package mapper;

import businessObjects.*;
import repositories.HallRepository;
import repositories.PerformanceRepository;
import repositories.SeatRepository;
import repositories.TicketRepository;
import resources.EventResource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventMapper  {

    public Event map(final EventResource eventResource, PerformanceRepository performanceRepository, HallRepository hallRepository, TicketRepository ticketRepository) throws Exception {
        try {
            String eventID=eventResource.getEventID();
          List<Ticket> tickets = new ArrayList<>(ticketRepository.findTicketsOfEvent(eventID));
            return buildEvent( eventResource,  performanceRepository, hallRepository, tickets);
        } catch (Exception e) {
            return null;
        }
    }
    public List<Event> map(List<EventResource> eventResources, PerformanceRepository performanceRepository, HallRepository hallRepository, TicketRepository ticketRepository) throws Exception {
        if ((eventResources!=null)&&(!eventResources.isEmpty())){
           List<Event> events=new ArrayList<>();
            for (EventResource eventResource:eventResources){
                events.add(map(eventResource, performanceRepository, hallRepository, ticketRepository));
            }
            return events;
        }else {
            return null;
        }
    }
    public List<Event> mapNewObject(List<EventResource> eventResources, PerformanceRepository performanceRepository, HallRepository hallRepository, SeatRepository seatRepository) {
        if ((eventResources!=null)&&(!eventResources.isEmpty())){
        List<Event> events=new ArrayList<>();
        for (EventResource eventResource:eventResources){
            events.add(mapNewObject(eventResource, performanceRepository, hallRepository, seatRepository));
        }
        return events;
        }else {
            return null;
        }
    }

    public Event mapNewObject(EventResource eventResource, PerformanceRepository performanceRepository, HallRepository hallRepository, SeatRepository seatRepository){
        try {
            Hall hall= hallRepository.findHallByName(eventResource.getHall());
            Price price= new Price(eventResource.getPrice());
          List <Ticket> tickets=new ArrayList<>();
            String eventID=eventResource.getEventID();
            for (Seat seat: seatRepository.findSeatsByHallName(hall.getHallName())){
                tickets.add(new Ticket(eventID,price, seat));
            }

            for (Ticket ticket: tickets){

            }
            return buildEvent( eventResource,  performanceRepository, hallRepository, tickets);

        } catch (Exception e) {
            return null;
        }

    }


private Event buildEvent(EventResource eventResource, PerformanceRepository performanceRepository, HallRepository hallRepository, List <Ticket> tickets){
   try {
       Performance performance=performanceRepository.findPerformanceByName(eventResource.getPerformance());
       String hallName= eventResource.getHall();
       Price price= new Price(eventResource.getPrice());
       SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.yyyy");
       Date date = formatterDate.parse(eventResource.getDate());
       SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm");
       Date time = formatterTime.parse(eventResource.getTime());
       String eventID=eventResource.getEventID();
       if ((performance!=null)&&(date!=null)&&(time!=null)&&(hallRepository.findHallByName(hallName)!=null)){
           return new Event(eventID,performance, date,time, hallName,price, tickets);
       }else {
           return null;
       }
   } catch (Exception e) {
       return null;
   }

}

}
