package Service;

import businessObjects.*;
import repositories.EventRepository;
import repositories.PerformanceRepository;

import java.sql.SQLException;
import java.util.ArrayList;


public class TheaterService {
   private final TheaterBuilding theaterBuilding;
   private final PerformanceRepository performanceRepository;
   private final EventRepository eventRepository;
    public TheaterService(PerformanceRepository performanceRepository, EventRepository eventRepository){
        this.theaterBuilding = new TheaterBuilding();
        this.performanceRepository = performanceRepository;
        this.eventRepository=eventRepository;

        loadRepertoireFromRepositoryUseCase();
        loadTheaterProgramFromRepositoryUseCase();

    }

    public void loadRepertoireFromRepositoryUseCase() {
        try {
            performanceRepository.loadRepertoireFromDB();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadTheaterProgramFromRepositoryUseCase() {
        eventRepository.loadTheaterProgramFromDB(theaterBuilding);
    }
    public PerformanceRepository getPerformancesRepository() {
        return performanceRepository;
    }
    public ArrayList<Event> showProgramUseCase () {
        return  eventRepository.findAllEvents();  }
    public ArrayList<Performance> showRepertoireUseCase () {
        return  performanceRepository.findAllPerformances();
    }
    public  void updateRepertoireUseCase ( ArrayList<Performance> performances) {
            performanceRepository.addPerformances(performances);
    }
    public void updateTheaterProgramUseCase(ArrayList<Event> eventList) {
        eventRepository.addEvents(eventList);
    }
    public TheaterBuilding getTheaterBuilding() {
        return theaterBuilding;
    }
    public PerformanceRepository getPerformanceRepository() {
        return performanceRepository;
    }
    public EventRepository getEventRepository() {
        return eventRepository;
    }

    public Ticket buyTicketUseCase(String ticket) {
       return eventRepository.buyTicket(ticket);
    }
}
