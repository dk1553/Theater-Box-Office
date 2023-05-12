package persistence.jdbc;

import businessObjects.Performance;
import db.JDBCService;
import repositories.PerformanceRepository;
import mapper.PerformanceMapper;
import mapper.PerformanceResourceMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    private void loadRepertoireFromDB() {
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
        List<Performance> cleanedPerformanceList = removeAlreadyExistingElements(removeDuplicates(performances));

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

    private List<Performance> removeAlreadyExistingElements(List<Performance> performances) {
        if ((performances != null) && (!performances.isEmpty())) {
            Set<Performance> toDelete = new HashSet<>();
            for (Performance performance : performances) {
                for (Performance exitingPerformance : this.performances) {


                    if (performance.getName().equalsIgnoreCase(exitingPerformance.getName())) {
                        toDelete.add(performance);
                    }
                }
            }
            performances.removeAll(toDelete);
        }

        return performances;
    }

    @Override
    public Boolean isEmpty() {
        return (performances == null) || (performances.isEmpty());
    }

    private List<Performance> removeDuplicates(List<Performance> performances) {
        Set<Performance> toDelete = new HashSet<>();
        if ((performances != null) && (!performances.isEmpty())) {


            for (Performance p : performances) {


                for (int i = performances.indexOf(p) + 1; i < performances.size(); i++) {

                    if (p.getName().equalsIgnoreCase(performances.get(i).getName())) {
                        toDelete.add(performances.get(i));
                    }
                }
            }
            if (!toDelete.isEmpty()) {
                for (Performance performance : toDelete) {
                    performances.remove(performance);
                }
            }
        }

        return performances;
    }

}
