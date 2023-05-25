package persistence.jdbc;

import db.JDBCService;
import entities.Performance;
import mapper.PerformanceMapper;
import mapper.PerformanceResourceMapper;
import repositories.PerformanceRepository;
import vo.CleanPerformanceList;

import java.util.ArrayList;
import java.util.List;

public class PerformanceRepositoryJDBC implements PerformanceRepository {
    private List<Performance> performances;


    public PerformanceRepositoryJDBC() throws RuntimeException {
        performances = new ArrayList<>();
        loadRepertoireFromDB();
    }

    @Override
    public List<Performance> findAllPerformances() {
        return performances;
    }

    @Override
    public Performance findPerformanceByName(String name) {
        for (Performance performance : performances) {
            if (performance.getName().equalsIgnoreCase(name)) {
                return performance;
            }
        }
        System.out.println("Performance doesn't exist");

        return null;
    }

    @Override
    public void loadRepertoireFromDB() {
        try {

            List<Performance> performancesFormDB = new PerformanceMapper().map(JDBCService.getRepertoire());
            if (!performancesFormDB.isEmpty()) {
                performances.addAll(performancesFormDB);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public Boolean addPerformances(List<Performance> performances) {
        List<Performance> cleanedPerformanceList = new CleanPerformanceList(performances, this.performances).getPerformanceList();

        if ((performances == null) || (performances.isEmpty())) {
            return false;
        }
        try {
            JDBCService.addPerformancesToDatabase(new PerformanceResourceMapper().map(cleanedPerformanceList));
            this.performances.addAll(performances);
            return true;
        } catch (Exception e) {
            return false;
        }
    }





}
