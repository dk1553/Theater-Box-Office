package mapper;

import businessObjects.*;
import resources.EventResource;

import java.util.ArrayList;
import java.util.List;

public class EventResourceMapper {
    public EventResource map(final Event event) {

       if (event!=null){
           TicketResourceMapper ticketResourceMapper= new TicketResourceMapper();
           try {
               return  new EventResource(event.getId(), event.getPerformance().getName(), event.getDate().toString(), event.getTime().toString(), event.getHallName(), event.getBasicPrice().toString(), ticketResourceMapper.map(event.getTickets()));
           } catch (Exception e) {
              return null;
           }
       }else{
           return null;
       }
    }

    public ArrayList<EventResource> map(List<Event> events) throws Exception {
        if ((events!=null)&&(!events.isEmpty())){
            ArrayList<EventResource> eventResources=new ArrayList<>();
            for (Event event:events){
                eventResources.add(map(event));
            }
            return eventResources;
        }else {
            return null;
        }

    }
}
