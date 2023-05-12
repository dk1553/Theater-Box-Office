package mapper;

import businessObjects.*;
import repositories.HallRepository;
import repositories.PerformanceRepository;
import resources.EventResource;

import java.text.SimpleDateFormat;
import java.util.*;

public class EventMapper {

    public Event map(final EventResource eventResource, PerformanceRepository performanceRepository, HallRepository hallRepository) throws Exception {
        if (Objects.isNull(eventResource )||Objects.isNull(performanceRepository )||Objects.isNull(hallRepository)) {
            return null;
        }
        try {
            return buildEvent(eventResource, performanceRepository, hallRepository);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Event> map(List<EventResource> eventResources, PerformanceRepository performanceRepository, HallRepository hallRepository) throws Exception {
        if ((Objects.isNull(eventResources)) || (eventResources.isEmpty())) {
            return Collections.emptyList();
        }
        List<Event> events = new ArrayList<>();
        for (EventResource eventResource : eventResources) {
            events.add(map(eventResource, performanceRepository, hallRepository));
        }
        return events;
    }

    public List<Event> mapNewObject(List<EventResource> eventResources, PerformanceRepository performanceRepository, HallRepository hallRepository) {
        if ((Objects.isNull(eventResources )) || (eventResources.isEmpty())) {
            return Collections.emptyList();
        }
        List<Event> events = new ArrayList<>();
        for (EventResource eventResource : eventResources) {
            events.add(mapNewObject(eventResource, performanceRepository, hallRepository));
        }
        return events;
    }

    public Event mapNewObject(EventResource eventResource, PerformanceRepository performanceRepository, HallRepository hallRepository) {
        try {
            return buildEvent(eventResource, performanceRepository, hallRepository);

        } catch (Exception e) {
            return null;
        }

    }


    private Event buildEvent(EventResource eventResource, PerformanceRepository performanceRepository, HallRepository hallRepository) {
        try {
            Performance performance = performanceRepository.findPerformanceByName(eventResource.getPerformance());
            String hallName = eventResource.getHall();
            Price price = new Price(eventResource.getPrice());
            SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.yyyy");
            Date date = formatterDate.parse(eventResource.getDate());
            SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm");
            Date time = formatterTime.parse(eventResource.getTime());
            String eventID = eventResource.getEventID();
            if ((performance != null) && (date != null) && (time != null) && (hallRepository.findHallByName(hallName) != null)) {
                return new Event(eventID, performance, date, time, hallName, price);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

}
