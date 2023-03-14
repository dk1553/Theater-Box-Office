package persistence;

import businessObjects.Performance;
import db.DBManager;
import repositories.PerformanceRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class Repertoire implements PerformanceRepository {
    private ArrayList<Performance> performances;

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

        return null;
    }


    private void loadRepertoireFromDB() throws SQLException, ClassNotFoundException {
        DBManager dbManagerRepertoire = new DBManager();
        performances.addAll(dbManagerRepertoire.getRepertoire());
        dbManagerRepertoire.close();
    }


    public Repertoire() throws RuntimeException {
        performances=new ArrayList<>();
        try {
            loadRepertoireFromDB();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void addPerformances(ArrayList<Performance> performances) {
        performances.addAll(performances);
        try {
            DBManager dbManagerAddPerformances = new DBManager();
            dbManagerAddPerformances.addPerformancesToDatabase(performances);
            dbManagerAddPerformances.close();
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
