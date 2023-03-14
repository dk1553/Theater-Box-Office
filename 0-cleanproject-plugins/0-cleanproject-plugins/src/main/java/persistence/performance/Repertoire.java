package persistence.performance;

import businessObjects.Performance;
import businessObjects.PerformanceRepository;

import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;

public class Repertoire implements PerformanceRepository {
   private ArrayList <Performance> performances;
    private EntityManager entityManager;
    private EntityManagerFactory emf;


    public Repertoire(){
        performances=new ArrayList<>();
        this.emf = Persistence.createEntityManagerFactory("student_pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public void addPerformance(Performance performance) {
        performances.add(performance);
    }

    public void deletePerformance(String name) {
        for (Performance p:performances){
            if (p.getName().equalsIgnoreCase(name)){
                performances.remove(p);
            }
        }
    }

    public void addSeveralPerformances(ArrayList<Performance> performances) {
        this.performances.addAll(performances);
    }

    public ArrayList<Performance> getPerformances() {
        return performances;
    }

    @Override
    public List<Performance> findAllPerformances() {
        return null;
    }

    @Override
    public List<Performance> findPerformanceByName(String name) {
        return null;
    }

    @Override
    public Performance save(Performance performance) {
        return null;
    }
}
