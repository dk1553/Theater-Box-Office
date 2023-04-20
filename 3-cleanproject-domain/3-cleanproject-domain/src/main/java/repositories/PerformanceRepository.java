package repositories;

import businessObjects.Performance;

import java.util.ArrayList;
import java.util.List;

public interface PerformanceRepository {
    List<Performance> findAllPerformances();

    Performance findPerformanceByName(String name);
  //  Boolean addPerformance(Performance performance);
    Boolean addPerformances(ArrayList<Performance> performances);
    Boolean isEmpty();

}
