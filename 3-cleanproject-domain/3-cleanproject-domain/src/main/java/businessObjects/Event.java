package businessObjects;

import java.util.ArrayList;
import java.util.Date;

public class Event {
    private final Performance performance;
    private final int id;
    private final Date date;
    private final Date time;
    private Hall hall;
    private String hallName;
    private final Price basicPrice;
    private ArrayList <Ticket> tickets;
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public Event (Performance performance, Date date, Date time, Hall hall, Price basicPrice) throws Exception {
        this.id=Identifier.getNewEventID();
        this.performance=performance;
        this.date=date;
        this.time=time;
        this.hall=hall;
        this.basicPrice=basicPrice;
        tickets=new ArrayList<>();
        for (Seat seat:hall.getSeats()){
            tickets.add(new Ticket(id,basicPrice, seat));
        }
        this.hallName= hall.getHallName();

    }

    public Event (Performance performance, Date date, Date time, String hall, Price basicPrice) throws Exception {
        this.id=Identifier.getNewEventID();
        this.performance=performance;
        this.date=date;
        this.time=time;
        this.hall=null;
        tickets=null;
        this.basicPrice=basicPrice;
        this.hallName=hall;

    }

    public void setHall (Hall hall) throws Exception {
        this.hall=hall;
        tickets=new ArrayList<>();
        for (Seat seat:hall.getSeats()){
            tickets.add(new Ticket(id,this.basicPrice, seat));
        }
    }

    public Performance getPerformance() {
        return performance;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Date getTime() {
        return time;
    }

    public Hall getHall() {
        return hall;
    }
    public String getHallName() {
        return hallName;
    }
}
