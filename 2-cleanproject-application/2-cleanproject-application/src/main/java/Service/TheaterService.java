package Service;

import businessObjects.*;
import repositories.EventRepository;
import repositories.PerformanceRepository;

import java.util.ArrayList;


public class TheaterService {
   private ArrayList <Hall> halls;
   private PerformanceRepository performanceRepository;
   private EventRepository eventRepository;
    public TheaterService(PerformanceRepository performanceRepository, EventRepository eventRepository){
        this.performanceRepository = performanceRepository;
        this.eventRepository=eventRepository;


        halls=new ArrayList<>();
        Hall smallHall= new Hall("Kleine Halle", 10,10,10,10,10,10,10);
        Hall bigHall = new Hall("Große Halle",10,10,10,15,20,15,20);
        halls.add(smallHall);
        halls.add(bigHall);
    }

    public PerformanceRepository getPerformancesRepository() {
        return performanceRepository;
    }
    public EventRepository getEventRepository() {
        return eventRepository;
    }

    public ArrayList<Event> showProgramUseCase () {
        return  eventRepository.findAllEvents();  }
    public ArrayList<Performance> showRepertoireUseCase () {
        return  performanceRepository.findAllPerformances();
    }
    public  void updateRepertoireUseCase ( ArrayList<Performance> performances) {
        for (Performance performance:performances){
            performanceRepository.addPerformance(performance);
        }

    }



    public Hall findHallByName(String hallName){
        for (Hall hall:halls){
            if (hall.getHallName().equalsIgnoreCase(hallName)){
                return hall;
            }
        }
        return  null;
    }
}
