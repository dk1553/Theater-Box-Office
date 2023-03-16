package Service;

import businessObjects.*;
import repositories.EventRepository;
import repositories.PerformanceRepository;

import java.util.ArrayList;


public class TheaterService {
   private final ArrayList <Hall> halls;
   private final PerformanceRepository performanceRepository;
   private final EventRepository eventRepository;
    public TheaterService(ArrayList<Hall> halls, PerformanceRepository performanceRepository, EventRepository eventRepository){
        this.halls = halls;
        this.performanceRepository = performanceRepository;
        this.eventRepository=eventRepository;

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
            performanceRepository.addPerformances(performances);
    }
    public Hall findHallByName(String hallName){
        for (Hall hall:halls){
            if (hall.getHallName().equalsIgnoreCase(hallName)){
                return hall;
            }
        }
        return  null;
    }

    public void updateTheaterProgramUseCase(ArrayList<Event> eventList) {
        eventRepository.addEvents(eventList);
    }
}
