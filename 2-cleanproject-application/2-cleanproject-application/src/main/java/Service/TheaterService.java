package Service;

import businessObjects.*;
import repositories.EventRepository;
import repositories.PerformanceRepository;
import repositories.TicketRepository;

import java.sql.SQLException;
import java.util.ArrayList;


public class TheaterService {
   private final TheaterBuilding theaterBuilding;
   private final PerformanceRepository performanceRepository;
   private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    public TheaterService(PerformanceRepository performanceRepository, EventRepository eventRepository, TicketRepository ticketRepository){
        this.theaterBuilding = new TheaterBuilding();
        this.performanceRepository = performanceRepository;
        this.eventRepository=eventRepository;
        this.ticketRepository=ticketRepository;

        loadRepertoireUseCase();
        loadTheaterProgramUseCase();
        loadTicketsUseCase();

    }

    public void loadRepertoireUseCase() {
        try {
            performanceRepository.loadRepertoireFromDB();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadTheaterProgramUseCase() {
        eventRepository.loadTheaterProgramFromDB(theaterBuilding);
    }
    public void loadTicketsUseCase() {
        try{
            ticketRepository.loadTicketsFromDB(theaterBuilding);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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
        ticketRepository.addTickets(eventList);
    }
    public TheaterBuilding getTheaterBuilding() {
        return theaterBuilding;
    }
    public PerformanceRepository getPerformanceRepository() {
        return performanceRepository;
    }
    public TicketRepository getTicketRepository(){return  ticketRepository;}
    public EventRepository getEventRepository() {
        return eventRepository;
    }

    public Ticket buyTicketUseCase(String ticket, String userFirstName, String userLastName) {

        try {

            User user = new User(userFirstName, userLastName);
            Boolean isIdentified=identifyUser(user);
            if (isIdentified){
                return ticketRepository.buyTicket(ticket);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    return null;
    }

    public Event showEventUseCase(String eventID){

        for (Event event: eventRepository.findAllEvents()){
            if (event.getId().equalsIgnoreCase(eventID)){
                for (Ticket t:  event.getTickets()){
                    System.out.println("test---"+t.isBooked());
                }
                    return event;
            }
        }
        return  null;
    }
    public Performance showPerformanceUseCase(String performanceName){
        for (Performance performance: performanceRepository.findAllPerformances()){
            if (performance.getName().equalsIgnoreCase(performanceName)){
                return performance;
            }
        }
        return  null;
    }

    public Boolean identifyUser(User user) {
        if ((!user.getFirstName().isBlank())&&(!user.getLastName().isBlank())){
            return true;
        }
        return false;
    }
}
