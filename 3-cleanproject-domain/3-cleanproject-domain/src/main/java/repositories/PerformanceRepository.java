package repositories;

import entities.Performance;

import java.util.List;

public interface PerformanceRepository {
    List<Performance> findAllPerformances();

    Performance findPerformanceByName(String name);

    void loadRepertoireFromDB();

    Boolean addPerformances(List<Performance> performances);



}
