package resources;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventResource {
    private String eventID;
    private String performance;
    private String date;
    private String time;
    private String hall;
    private String price;
    private List<TicketResource> ticketResources;


    public EventResource(String eventID, String performance, String date, String time, String hall, String price, List<TicketResource> ticketResources) {
        this.eventID = eventID;
        this.date = date;
        this.performance = performance;
        this.hall = hall;
        this.time = time;
        this.price = price;
        this.ticketResources = ticketResources;
    }

    public EventResource(String eventID, String performance, String date, String time, String hall, String price) {
        this.eventID = eventID;
        this.date = date;
        this.performance = performance;
        this.hall = hall;
        this.time = time;
        this.price = price;
        this.ticketResources = new ArrayList<>();
    }

    public EventResource(String performance, String date, String time, String hall, String price) {
        this.eventID = UUID.randomUUID().toString();
        this.date = date;
        this.performance = performance;
        this.hall = hall;
        this.time = time;
        this.price = price;
        this.ticketResources = new ArrayList<>();

    }

    public String getPerformance() {
        return performance;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getHall() {
        return hall;
    }

    public String getPrice() {
        return price;
    }

    public String getEventID() {
        return eventID;
    }

    public List<TicketResource> getTicketResources() {
        return ticketResources;
    }

    public int getNumberOfBookedSeats() {
        int result = 0;
        for (TicketResource ticket : ticketResources) {
            if (ticket.isBooked()) {
                result++;
            }
        }
        return result;
    }

    public int getNumberOfAvailableSeats() {
        int result = 0;
        for (TicketResource ticket : ticketResources) {
            if (!ticket.isBooked()) {
                result++;
            }
        }
        return result;
    }
}

