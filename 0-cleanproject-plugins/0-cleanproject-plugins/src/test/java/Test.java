import businessObjects.Event;
import businessObjects.Performance;
import businessObjects.TheaterBuilding;
import mapper.EventMapper;
import mapper.EventResourceMapper;
import persistence.EventRepositoryJDBC;
import persistence.PerformanceRepositoryJDBC;
import persistence.TicketRepositoryJDBC;
import resources.EventResource;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Test {

     @org.junit.Test
    public void testEventMappingWithoutTickets(){
        try {
            //create test eventResource without tickets
            EventResourceMapper eventResourceMapper= new EventResourceMapper();
            EventMapper eventMapper= new EventMapper();
            TheaterBuilding theaterBuilding= new TheaterBuilding();
            PerformanceRepositoryJDBC performanceRepository= new PerformanceRepositoryJDBC();
            TicketRepositoryJDBC ticketRepository= new TicketRepositoryJDBC(theaterBuilding);
            EventRepositoryJDBC eventRepositoryJDBC= new EventRepositoryJDBC(theaterBuilding, ticketRepository, performanceRepository);
            if (!performanceRepository.isEmpty()){
            EventResource eventResource=eventResourceMapper.map(eventRepositoryJDBC.findAllEvents().get(0));
            //compare eventResource with event
            try{
                Event event=eventMapper.map(eventResource, performanceRepository, theaterBuilding, ticketRepository);
                assertEquals(eventResource.getEventID(), event.getId());
                assertEquals(eventResource.getPerformance(), event.getPerformance().getName());
                assertEquals(eventResource.getHall(), event.getHallName());
                assertEquals(eventResource.getDate(), event.getDate());
                assertEquals(eventResource.getTime(), eventResource.getTime());
                assertEquals(eventResource.getTicketResources().size(), event.getTickets().size());

            } catch (Exception e) {
               fail("Event mapping is failed");
            }}
        } catch (Exception e) {
            fail();
        }
    }

    @org.junit.Test
    public void testEventMappingNewObject(){
        try {
            //create test eventResource
            EventResourceMapper eventResourceMapper= new EventResourceMapper();
            EventMapper eventMapper= new EventMapper();
            TheaterBuilding theaterBuilding= new TheaterBuilding();
            PerformanceRepositoryJDBC performanceRepository= new PerformanceRepositoryJDBC();
            TicketRepositoryJDBC ticketRepository= new TicketRepositoryJDBC(theaterBuilding);
            EventRepositoryJDBC eventRepositoryJDBC = new EventRepositoryJDBC(theaterBuilding, ticketRepository, performanceRepository);
            EventResource eventResource= eventResourceMapper.map(eventRepositoryJDBC.findAllEvents().get(0));
            try{
                //compare eventResource  with event
                Event event=eventMapper.mapNewObject(eventResource, performanceRepository, theaterBuilding, ticketRepository);
                assertEquals(eventResource.getEventID(), event.getId());
                assertEquals(eventResource.getPerformance(), event.getPerformance().getName());
                assertEquals(eventResource.getHall(), event.getHallName());
                assertEquals(eventResource.getDate(), event.getDate());
                assertEquals(eventResource.getTime(), eventResource.getTime());
                assertEquals(theaterBuilding.findHallByName(eventResource.getHall()).getGuestLimit(), event.getTickets().size());

            } catch (Exception e) {
                fail("Event mapping is failed");
            }
        } catch (Exception e) {
            fail();
        }
    }
}
