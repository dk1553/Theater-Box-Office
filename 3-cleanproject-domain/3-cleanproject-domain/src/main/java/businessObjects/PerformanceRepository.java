package businessObjects;

import java.util.List;

public interface PerformanceRepository {

    List<Performance> findAllPerformances();

    List<Performance> findPerformanceByName(String name);

    Performance save(Performance performance);
     void deletePerformance(String name);
}
