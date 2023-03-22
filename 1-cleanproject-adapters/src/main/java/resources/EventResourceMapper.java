package resources;

import businessObjects.*;
import repositories.PerformanceRepository;
import repositories.TicketRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EventResourceMapper {
    public EventResource map(final Event event) throws Exception {
        return  new EventResource(event.getId(), event.getPerformance().getName(), event.getDate().toString(), event.getTime().toString(), event.getHallName(), event.getBasicPrice().toString());

    }

    public ArrayList<EventResource> map(ArrayList<Event> events) throws Exception {
        ArrayList<EventResource> eventResources=new ArrayList<>();
        for (Event event:events){
            eventResources.add(map(event));
        }
        return eventResources;
    }
}
