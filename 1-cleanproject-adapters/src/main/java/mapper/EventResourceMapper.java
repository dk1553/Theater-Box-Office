package mapper;

import businessObjects.*;
import repositories.TicketRepository;
import resources.EventResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventResourceMapper {
    public EventResource map(Event event, TicketRepository ticketRepository) {

        if (event == null) {
            return null;
        }
        TicketResourceMapper ticketResourceMapper = new TicketResourceMapper();
        try {
            return new EventResource(event.getId(), event.getPerformance().getName(), event.getDate(), event.getTime(), event.getHallName(), event.getBasicPrice().toString(), ticketResourceMapper.map(ticketRepository.findTicketsOfEvent(event.getId())));
        } catch (Exception e) {
            return null;
        }
    }

    public List<EventResource> map(List<Event> events, TicketRepository ticketRepository) throws Exception {
        if ((events == null) || (events.isEmpty())) {
            return Collections.emptyList();
        }
        List<EventResource> eventResources = new ArrayList<>();
        for (Event event : events) {
            eventResources.add(map(event, ticketRepository));
        }
        return eventResources;
    }
}
