package Service;

import businessObjects.*;

import java.util.ArrayList;


public class TheaterService {
   private Repertoire repertoire;
   private TheaterProgram theaterProgram;
   private ArrayList <Hall> halls;
    public TheaterService(){
        repertoire = new Repertoire();
        theaterProgram=new TheaterProgram();
        halls=new ArrayList<>();
        Hall smallHall= new Hall("Kleine Halle", 10,10,10,10,10,10,10);
        Hall bigHall = new Hall("Große Halle",10,10,10,15,20,15,20);
        halls.add(smallHall);
        halls.add(bigHall);
    }

    public void loadRepertoire(ArrayList <Performance> performances) {
        this.repertoire.addSeveralPerformances(performances);
    }
    public Repertoire getRepertoire(){
        return this.repertoire;

    }
    public void loadTheaterProgram(ArrayList <Event> events) {
        this.theaterProgram.addSeveralEvents(events);
    }
    public TheaterProgram getTheaterProgram() {
        return theaterProgram;
    }
    public Performance findPerformance(String performanceName){
        for (Performance performance:repertoire.getPerformances()){
            if (performance.getName().equalsIgnoreCase(performanceName)){
                return performance;
            }
        }
        return null;
    }
    public Hall findHall(String hallName){
        for (Hall hall:halls){
            if (hall.getHallName().equalsIgnoreCase(hallName)){
                return hall;
            }
        }
        return  null;
    }
}
