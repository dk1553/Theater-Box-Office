package resources;

import Service.TheaterService;
import businessObjects.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Function;

public class EventMapper  {

    public Event map(final EventResource eventResource, TheaterService service) throws Exception {

        Performance performance=service.getPerformanceRepository().findPerformanceByName(eventResource.getPerformance());
        Hall hall= service.getTheaterBuilding().findHallByName(eventResource.getHall());
        Price price= new Price(eventResource.getPrice());
        SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.yyyy");
        Date date = formatterDate.parse(eventResource.getDate());
        SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm");
        Date time = formatterTime.parse(eventResource.getTime());

        if ((performance!=null)&&(date!=null)&&(time!=null)&&(hall!=null)){
            return new Event(performance, date,time, hall,price);
        }
        return null;

    }

    public ArrayList<Event> map(ArrayList<EventResource> eventResources, TheaterService service) throws Exception {
        ArrayList<Event> events=new ArrayList<>();
        for (EventResource eventResource:eventResources){
            events.add(map(eventResource, service));
        }
        return events;
    }
}
