import java.util.ArrayList;

public class Repertoire {
   private ArrayList <Performance> performances;

    public Repertoire(){
        performances=new ArrayList<>();
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
}
