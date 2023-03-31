package repositories;

import businessObjects.Performance;

import java.util.ArrayList;

public interface PerformanceRepository {
    ArrayList<Performance> findAllPerformances();

    Performance findPerformanceByName(String name);
  //  Boolean addPerformance(Performance performance);
    Boolean addPerformances(ArrayList<Performance> performances);
    Boolean isEmpty();

}
