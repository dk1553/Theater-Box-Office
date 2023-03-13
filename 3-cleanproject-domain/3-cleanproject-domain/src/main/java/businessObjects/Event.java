package businessObjects;

import java.util.ArrayList;
import java.util.Date;

public class Event {
    private final Performance performance;
    private final int id;
    private final Date date;
    private final Date time;
    private final Hall hall;
    private final ArrayList <Ticket> tickets;
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public Event (Performance performance, Date date, Date time, Hall hall, Price basicPrice) throws Exception {
        this.id=Identifier.getNewEventID();
        this.performance=performance;
        this.date=date;
        this.time=time;
        this.hall=hall;
        tickets=new ArrayList<>();
        for (Seat seat:hall.getSeats()){
            tickets.add(new Ticket(id,basicPrice, seat));
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
}
