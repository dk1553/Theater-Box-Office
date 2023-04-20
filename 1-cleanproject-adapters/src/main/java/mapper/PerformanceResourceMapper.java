package mapper;

import businessObjects.Performance;
import resources.PerformanceResource;

import java.util.ArrayList;
import java.util.List;

public class PerformanceResourceMapper {
    public PerformanceResource map(final Performance performance) throws Exception {
        if (performance != null) {
            return new PerformanceResource(performance.getName(), performance.getDescription());
        } else {
            return null;
        }


    }

    public List<PerformanceResource> map(List<Performance> performances) throws Exception {
        if ((performances != null) && (!performances.isEmpty())) {
            List<PerformanceResource> performanceResources = new ArrayList<>();
            for (Performance performance : performances) {
                performanceResources.add(map(performance));
            }
            return performanceResources;
        } else {
            return null;
        }
    }
}
