package mapper;

import entities.Performance;
import resources.PerformanceResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PerformanceResourceMapper {
    public PerformanceResource map(final Performance performance) throws Exception {
        if (Objects.isNull(performance)) {
            return null;
        }
        return new PerformanceResource(performance.getName(), performance.getDescription());


    }

    public List<PerformanceResource> map(List<Performance> performances) throws Exception {
        if ((Objects.isNull(performances)) || (performances.isEmpty())) {
            return Collections.emptyList();
        }
        List<PerformanceResource> performanceResources = new ArrayList<>();
        for (Performance performance : performances) {
            performanceResources.add(map(performance));
        }
        return performanceResources;

    }
}
