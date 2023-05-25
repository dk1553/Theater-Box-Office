package vo;

import entities.Performance;

import java.util.*;

public final class CleanPerformanceList {
    final List<Performance> performanceList;

    public CleanPerformanceList(List<Performance> newPerformances, List<Performance> oldPerformances) {
        performanceList = removeAlreadyExistingElements(removeDuplicates(newPerformances), oldPerformances);

    }

    private List<Performance> removeDuplicates(List<Performance> newPerformances) {
        Set<Performance> toDelete = new HashSet<>();
        if ((Objects.isNull(newPerformances)) || (newPerformances.isEmpty())) {
            return Collections.emptyList();
        }


        for (Performance p : newPerformances) {
            for (int i = newPerformances.indexOf(p) + 1; i < newPerformances.size(); i++) {
                if (p.getName().equalsIgnoreCase(newPerformances.get(i).getName())) {
                    toDelete.add(newPerformances.get(i));
                }
            }
        }
        newPerformances.removeAll(toDelete);

        return newPerformances;
    }

    private List<Performance> removeAlreadyExistingElements(List<Performance> newPerformances, List<Performance> oldPerformances) {
        if ((Objects.isNull(newPerformances)) || (newPerformances.isEmpty())) {
            return Collections.emptyList();
        }
        Set<Performance> toDelete = new HashSet<>();
        for (Performance performance : newPerformances) {
            for (Performance exitingPerformance : oldPerformances) {
                if (performance.getName().equalsIgnoreCase(exitingPerformance.getName())) {
                    toDelete.add(performance);
                }
            }
        }
        newPerformances.removeAll(toDelete);


        return newPerformances;
    }

    public List<Performance> getPerformanceList() {
        return performanceList;
    }
}
