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
    public TheaterService(TheaterBuilding theaterBuilding, PerformanceRepository performanceRepository, EventRepository eventRepository, TicketRepository ticketRepository){
        this.theaterBuilding = theaterBuilding;
        this.performanceRepository = performanceRepository;
        this.eventRepository=eventRepository;
        this.ticketRepository=ticketRepository;
    }


    public PerformanceRepository getPerformancesRepository() {
        return performanceRepository;
    }
    public ArrayList<Event> showProgramUseCase () {
        return  eventRepository.findAllEvents();  }
    public ArrayList<Performance> showRepertoireUseCase () {
        return  performanceRepository.findAllPerformances();
    }
    public  boolean updateRepertoireUseCase ( ArrayList<Performance> performances) {
           try {
               performanceRepository.addPerformances(performances);
               return true;
           } catch (Exception e) {
               return false;
           }
    }
    public boolean updateTheaterProgramUseCase(ArrayList<Event> eventList) {
        try{
            System.out.println("wow--"+eventList.get(0).getTickets().size());
            eventRepository.addEvents(eventList);
            ticketRepository.addTickets(eventList);

            return true;
        } catch (Exception e) {
           return false;
        }

    }
    public TheaterBuilding getTheaterBuilding() {
        return theaterBuilding;
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

    public PerformanceRepository getPerformanceRepository() {
        return performanceRepository;
    }

    public EventRepository getEventRepository() {
        return eventRepository;
    }

    public TicketRepository getTicketRepository() {
        return ticketRepository;
    }
}
