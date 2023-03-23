package repositories;

import businessObjects.Performance;
import businessObjects.TheaterBuilding;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PerformanceRepository {
    ArrayList<Performance> findAllPerformances();

    Performance findPerformanceByName(String name);
    void addPerformance(Performance performance);
    void addPerformances(ArrayList<Performance> performances);

}
