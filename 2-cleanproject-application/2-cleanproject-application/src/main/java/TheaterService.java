import java.util.ArrayList;


public class TheaterService {
   private Repertoire repertoire;
    public TheaterService(){
        repertoire = new Repertoire();
    }

    public void loadRepertoire(ArrayList <Performance> performances) {
        this.repertoire.addSeveralPerformances(performances);
    }
    public Repertoire getRepertoire(){
        return this.repertoire;

    }
}
