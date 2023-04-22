package mapper;

import businessObjects.*;
import resources.EventResource;

import java.util.ArrayList;
import java.util.List;

public class EventResourceMapper {
    public EventResource map(final Event event) {

        if (event == null) {
            return null;
        }
        TicketResourceMapper ticketResourceMapper = new TicketResourceMapper();
        try {
            return new EventResource(event.getId(), event.getPerformance().getName(), event.getDate(), event.getTime(), event.getHallName(), event.getBasicPrice().toString(), ticketResourceMapper.map(event.getTickets()));
        } catch (Exception e) {
            return null;
        }
    }

    public List<EventResource> map(List<Event> events) throws Exception {
        if ((events == null) && (events.isEmpty())) {
            return null;
        }
        List<EventResource> eventResources = new ArrayList<>();
        for (Event event : events) {
            eventResources.add(map(event));
        }
        return eventResources;
    }
}
