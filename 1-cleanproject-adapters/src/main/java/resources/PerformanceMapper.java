package resources;

import Service.TheaterService;
import businessObjects.Event;
import businessObjects.Hall;
import businessObjects.Performance;
import businessObjects.Price;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PerformanceMapper {
    public Performance map(final PerformanceResource performanceResource) throws Exception {
      return new Performance(performanceResource.getName(),performanceResource.getDescription());

    }

    public ArrayList<Performance> map(ArrayList<PerformanceResource> performanceResources) throws Exception {
        ArrayList<Performance> performances=new ArrayList<>();
        for (PerformanceResource performanceResource:performanceResources){
            performances.add(map(performanceResource));
        }
        return performances;
    }
}
