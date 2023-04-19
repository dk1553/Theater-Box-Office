package persistence;

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
    public Boolean addPerformances(ArrayList<Performance> performances) {
        performances=removeDuplicates(performances);
        performances=removeAlreadyExistingElements(performances);

        if ((performances!=null)&&(!performances.isEmpty())){
        try {
            JDBCService jdbcService = new JDBCService();
            jdbcService.addPerformancesToDatabase(performanceResourceMapper.map(performances));
            jdbcService.close();
            this.performances.addAll(performances);
            return true;
        } catch (Exception e) {
            return false;
        }
        }else {
            return false;
        }
    }

  /*  @Override
    public Boolean addPerformance(Performance performance) {
        if (!exist(performance, this.performances)){
            performances.add(performance);
            try {
                JDBCService jdbcService = new JDBCService();
                jdbcService.addPerformancesToDatabase(performanceResourceMapper.map(performances));
                jdbcService.close();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }return true;
        }else{
            return false;
        }

    }*/

    private ArrayList <Performance> removeAlreadyExistingElements(ArrayList <Performance> performances){
        Set <Performance> toDelete = new HashSet<>();
        if ((performances!=null)&&(!performances.isEmpty())){
        for (Performance performance:performances){
             for (Performance exitingPerformance:this.performances){


                if (performance.getName().equalsIgnoreCase(exitingPerformance.getName())){
                    toDelete.add(performance);
                }
             }
        }}
        performances.removeAll(toDelete);
        return performances;
    }

    @Override
    public Boolean isEmpty(){
        if ((performances!=null)&&(!performances.isEmpty())){
            return  false;
        }else {
            return true;
        }
    }

    private ArrayList <Performance> removeDuplicates(ArrayList <Performance> performances){
        Set <Performance> toDelete= new HashSet<>();
        if ((performances!=null)&&(!performances.isEmpty())){






        for (Performance p:performances){


            for (int i=performances.indexOf(p)+1; i<performances.size(); i++){

                if (p.getName().equalsIgnoreCase(performances.get(i).getName())){
                  toDelete.add(performances.get(i));
                }
            }
        }
        if (!toDelete.isEmpty()){
        for (Performance performance:toDelete){
            performances.remove(performance);
        }}
        }

        return  performances;
    }

}
