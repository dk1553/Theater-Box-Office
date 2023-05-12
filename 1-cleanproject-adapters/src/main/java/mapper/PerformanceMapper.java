package mapper;

import businessObjects.Performance;
import resources.PerformanceResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PerformanceMapper {
    public Performance map(final PerformanceResource performanceResource) throws Exception {
        if (Objects.isNull(performanceResource )) {
            return null;
        }
        return new Performance(performanceResource.getName(), performanceResource.getDescription());
    }

    public List<Performance> map(List<PerformanceResource> performanceResources) throws Exception {
        if ((Objects.isNull(performanceResources )) || (performanceResources.isEmpty())) {
            return Collections.emptyList();
        }
        List<Performance> performances = new ArrayList<>();
        for (PerformanceResource performanceResource : performanceResources) {
            performances.add(map(performanceResource));
        }
        return performances;
    }
}
