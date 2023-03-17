package persistence;

import businessObjects.Performance;
import businessObjects.TheaterBuilding;
import db.DBManager;
import repositories.PerformanceRepository;
import resources.EventMapper;
import resources.PerformanceMapper;

import java.sql.SQLException;
import java.util.ArrayList;

public class Repertoire implements PerformanceRepository {
    private ArrayList<Performance> performances;


    public Repertoire() throws RuntimeException {
        performances=new ArrayList<>();
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

    @Override
    public void loadRepertoireFromDB() throws SQLException, ClassNotFoundException {
        performances=new ArrayList<>();
        DBManager dbManagerRepertoire = new DBManager();
        ArrayList <Performance> performancesFormDB=dbManagerRepertoire.getRepertoire();
        if (!performancesFormDB.isEmpty()){
            performances.addAll(performancesFormDB);
        }

        dbManagerRepertoire.close();
    }



    @Override
    public void addPerformances(ArrayList<Performance> performances) {
        try {
            DBManager dbManagerAddPerformances = new DBManager();
            dbManagerAddPerformances.addPerformancesToDatabase(performances);
            dbManagerAddPerformances.close();
            this.performances.addAll(performances);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public void deletePerformanceByName(String name) {
        for (Performance p:performances){
            if (p.getName().equalsIgnoreCase(name)){
                performances.remove(p);
            }
        }
    }

    @Override
    public void addPerformance(Performance performance) {
        performances.add(performance);
        try {
            DBManager dbManagerAddPerformances = new DBManager();
            dbManagerAddPerformances.addPerformancesToDatabase(performances);
            dbManagerAddPerformances.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
