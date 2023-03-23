package mapper;

import businessObjects.Performance;
import resources.PerformanceResource;

import java.util.ArrayList;

public class PerformanceResourceMapper {
    public PerformanceResource map(final Performance performance) throws Exception {
        return new PerformanceResource( performance.getName(),performance.getDescription());

    }

    public ArrayList<PerformanceResource> map(ArrayList<Performance> performances) throws Exception {
        ArrayList<PerformanceResource> performanceResources=new ArrayList<>();
        for (Performance performance:performances){
            performanceResources.add(map(performance));
        }
        return performanceResources;
    }
}
