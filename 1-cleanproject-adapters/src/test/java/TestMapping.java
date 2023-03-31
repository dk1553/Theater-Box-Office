import businessObjects.*;
import mapper.EventMapper;
import mapper.EventResourceMapper;
import resources.EventResource;
import resources.TicketResource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

public class TestMapping {
    @org.junit.Test
    public void testEventResourceMappingWithoutTickets(){
        try {
            //create test event without tickets
            EventResourceMapper eventResourceMapper= new EventResourceMapper();
            TheaterBuilding theaterBuilding= new TheaterBuilding();
            SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.yyyy");
            Date date = formatterDate.parse("10.12.2022");
            SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm");
            Date time = formatterTime.parse("10:30");
            ArrayList <Ticket> tickets= new ArrayList<>();
            Event event= new Event(UUID.randomUUID().toString(), new Performance("test","test"), date, time,theaterBuilding.findHallByName("Kleine Halle"), new Price("10"), tickets);

            //compare event with eventResource
               try {
                   EventResource eventResource=eventResourceMapper.map(event);
                   assertEquals(event.getId(), eventResource.getEventID());
                   assertEquals(event.getPerformance().getName(), eventResource.getPerformance());
                   assertEquals(event.getHallName(), eventResource.getHall());
                   assertEquals(event.getDate(), eventResource.getDate());
                   assertEquals(event.getTime(), eventResource.getTime());
                   assertEquals(event.getTickets().size(), eventResource.getTicketResources().size());
               } catch (Exception e) {
                   fail("EventResource mapping is failed");
               }
        } catch (Exception e) {
            fail();
        }
    }

    @org.junit.Test
    public void testEventResourceMappingWithTickets(){
        try {
            //create test event with tickets
            EventResourceMapper eventResourceMapper= new EventResourceMapper();
            TheaterBuilding theaterBuilding= new TheaterBuilding();
            SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.yyyy");
            Date date = formatterDate.parse("10.12.2022");
            SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm");
            Date time = formatterTime.parse("10:30");
            String eventID=UUID.randomUUID().toString();
            ArrayList <Ticket> tickets= new ArrayList<>();
            tickets.add(new Ticket(UUID.randomUUID().toString(), eventID, new Price("10"), theaterBuilding.findHallByName("Kleine Halle").getSeats()[1], true, UUID.randomUUID().toString()));
            tickets.add(new Ticket(UUID.randomUUID().toString(), eventID, new Price("10"), theaterBuilding.findHallByName("Kleine Halle").getSeats()[2], false, UUID.randomUUID().toString()));
            tickets.add(new Ticket(UUID.randomUUID().toString(), eventID, new Price("10"), theaterBuilding.findHallByName("Kleine Halle").getSeats()[3], false, UUID.randomUUID().toString()));
            Event event= new Event(eventID, new Performance("test","test"), date, time,theaterBuilding.findHallByName("Kleine Halle"), new Price("10"), tickets);

            //compare event with eventResource
            try {
                EventResource eventResource=eventResourceMapper.map(event);
                assertEquals(event.getId(), eventResource.getEventID());
                assertEquals(event.getPerformance().getName(), eventResource.getPerformance());
                assertEquals(event.getHallName(), eventResource.getHall());
                assertEquals(event.getDate(), eventResource.getDate());
                assertEquals(event.getTime(), eventResource.getTime());
                assertEquals(event.getTickets().size(), eventResource.getTicketResources().size());
            } catch (Exception e) {
                fail("EventResource mapping is failed");
            }
        } catch (Exception e) {
            fail();
        }
    }

    @org.junit.Test
    public void testTicketResourceMapping(){
        try {
            //create test event with tickets
            EventResourceMapper eventResourceMapper= new EventResourceMapper();
            TheaterBuilding theaterBuilding= new TheaterBuilding();
            SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.yyyy");
            Date date = formatterDate.parse("10.12.2022");
            SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm");
            Date time = formatterTime.parse("10:30");
            String eventID=UUID.randomUUID().toString();
            ArrayList <Ticket> tickets= new ArrayList<>();
            tickets.add(new Ticket(UUID.randomUUID().toString(), eventID, new Price("10"), theaterBuilding.findHallByName("Kleine Halle").getSeats()[1], true, UUID.randomUUID().toString()));
            tickets.add(new Ticket(UUID.randomUUID().toString(), eventID, new Price("10"), theaterBuilding.findHallByName("Kleine Halle").getSeats()[2], false, UUID.randomUUID().toString()));
            tickets.add(new Ticket(UUID.randomUUID().toString(), eventID, new Price("10"), theaterBuilding.findHallByName("Kleine Halle").getSeats()[3], false, UUID.randomUUID().toString()));
            Event event= new Event(eventID, new Performance("test","test"), date, time,theaterBuilding.findHallByName("Kleine Halle"), new Price("10"), tickets);

            //compare event with eventResource
            try {
                EventResource eventResource=eventResourceMapper.map(event);
                ArrayList <TicketResource> ticketResources = eventResource.getTicketResources();
               for (int i=0; i<tickets.size();i++){
                   assertEquals(tickets.get(i).getEventID(), ticketResources.get(i).getEventID());
                   assertEquals(tickets.get(i).getId(), ticketResources.get(i).getId());
                   assertEquals(tickets.get(i).getPrice().toString(), ticketResources.get(i).getPrice());
                   assertEquals(tickets.get(i).getValidationCode(), ticketResources.get(i).getValidationCode());
                   assertEquals(tickets.get(i).getSeat().getSeatNumber(), ticketResources.get(i).getSeat());


               }
            } catch (Exception e) {
                fail("EventResource mapping is failed");
            }
        } catch (Exception e) {
            fail();
        }
    }
}
