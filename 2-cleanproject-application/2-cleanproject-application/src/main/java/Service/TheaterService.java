package Service;

import entities.*;
import repositories.*;
import services.BookOneWayTicketDomainService;
import services.BookYearTicketDomainService;

import java.util.ArrayList;
import java.util.List;


public class TheaterService {
    private final HallRepository hallRepository;
    private final PerformanceRepository performanceRepository;
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;
    private final BookOneWayTicketDomainService bookOneWayTicketDomainService;
    private final BookYearTicketDomainService bookYearTicketDomainService;


    public TheaterService(HallRepository hallRepository, SeatRepository seatRepository, PerformanceRepository performanceRepository, EventRepository eventRepository, TicketRepository ticketRepository, BookOneWayTicketDomainService bookOneWayTicketDomainService, BookYearTicketDomainService bookYearTicketDomainService) {
        this.hallRepository = hallRepository;
        this.seatRepository = seatRepository;
        this.performanceRepository = performanceRepository;
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
        this.bookOneWayTicketDomainService = bookOneWayTicketDomainService;
        this.bookYearTicketDomainService = bookYearTicketDomainService;
    }

    public List<Event> showProgramUseCase() {
        return eventRepository.findAllEvents();
    }

    public List<Performance> showRepertoireUseCase() {
        return performanceRepository.findAllPerformances();
    }

    public boolean updateRepertoireUseCase(List<Performance> performances) {
        try {
            return performanceRepository.addPerformances(performances);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateTheaterProgramUseCase(List<Event> eventList) {
        try {
            eventRepository.addEvents(eventList, ticketRepository);
            List<Ticket> tickets = new ArrayList<>();
            for (Event event : eventList) {
                for (Seat seat : seatRepository.findSeatsByHallName(event.getHallName())) {
                    tickets.add(new OneWayTicket(event.getId(), event.getBasicPrice(), seat));
                }
            }
            ticketRepository.addTickets(tickets);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public HallRepository getHallRepository() {
        return hallRepository;
    }

    public Ticket buyTicketUseCase(String ticket, String userFirstName, String userLastName) {
        try {
            Boolean userIsVerified = verifyUser(userFirstName, userLastName);
            if (userIsVerified) {

                return this.bookOneWayTicketDomainService.bookTicket(ticketRepository, ticket);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Ticket buyYearTicketUseCase(String userFirstName, String userLastName) {
        try {
            Boolean userIsVerified = verifyUser(userFirstName, userLastName);
            if (userIsVerified) {
                return this.bookYearTicketDomainService.bookTicket(ticketRepository);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Event showEventUseCase(String eventID) {

        for (Event event : eventRepository.findAllEvents()) {
            if (event.getId().equalsIgnoreCase(eventID)) {
                return event;
            }
        }
        return null;
    }

    public Performance showPerformanceUseCase(String performanceName) {
        for (Performance performance : performanceRepository.findAllPerformances()) {
            if (performance.getName().equalsIgnoreCase(performanceName)) {
                return performance;
            }
        }
        return null;
    }

    public Boolean verifyUser(String fName, String lName) {
        return (!fName.isBlank()) && (!lName.isBlank());
    }

    public Boolean verifyAdmin(String username, String password) {
        return (username.equalsIgnoreCase("admin")) && (password.equalsIgnoreCase("1234"));
    }

    public PerformanceRepository getPerformanceRepository() {
        return performanceRepository;
    }

    public TicketRepository getTicketRepository() {
        return ticketRepository;
    }


}
