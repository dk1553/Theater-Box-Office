package persistence;

import businessObjects.Performance;
import db.JDBCService;
import repositories.PerformanceRepository;
import mapper.PerformanceMapper;
import mapper.PerformanceResourceMapper;

import java.util.ArrayList;

public class PerformanceRepositoryJDBC implements PerformanceRepository {
    private ArrayList<Performance> performances;
    private  static PerformanceMapper performanceMapper;
    private static PerformanceResourceMapper performanceResourceMapper;



    public PerformanceRepositoryJDBC() throws RuntimeException {
        performances=new ArrayList<>();
        performanceMapper=new PerformanceMapper();
        performanceResourceMapper = new PerformanceResourceMapper();
        loadRepertoireFromDB();
    }

    @Override
    public ArrayList<Performance> findAllPerformances() {
        return performances;
    }

    @Override
    public Performance findPerformanceByName(String name) {
        for (Performance performance:performances){
            if (performance.getName().equalsIgnoreCase(name)){
                return performance;
            }
        }
        System.out.println("Performance doesn't exist");

        return null;
    }


    private void loadRepertoireFromDB() {
        try {
            JDBCService jdbcService = new JDBCService();
            ArrayList <Performance> performancesFormDB=performanceMapper.map(jdbcService.getRepertoire());
            if (!performancesFormDB.isEmpty()){
                performances.addAll(performancesFormDB);
            }
            jdbcService.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



    @Override
    public void addPerformances(ArrayList<Performance> performances) {
        try {
            JDBCService jdbcService = new JDBCService();
            jdbcService.addPerformancesToDatabase(performanceResourceMapper.map(performances));
            jdbcService.close();
            this.performances.addAll(performances);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addPerformance(Performance performance) {
        performances.add(performance);
        try {
            JDBCService jdbcService = new JDBCService();
            jdbcService.addPerformancesToDatabase(performanceResourceMapper.map(performances));
            jdbcService.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
