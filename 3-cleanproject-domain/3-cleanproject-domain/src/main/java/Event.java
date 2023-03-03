import java.util.ArrayList;
import java.util.Date;

public class Event {
    private Performance performance;
    private int id;
    private Date date;
    private Date time;
    private Hall hall;
    private Seat[] freeSeats;
    private Seat[] bookedSeats;
    private ArrayList <Ticket> tickets;

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public Event (int id, Performance performance, Date date, Date time, Hall hall, Price basicPrice) throws Exception {
        this.id=id;
        this.performance=performance;
        this.date=date;
        this.time=time;
        this.hall=hall;
        this.freeSeats=hall.getSeats();
        this.bookedSeats=null;
        tickets=new ArrayList<>();
        for (Seat seat:hall.getSeats()){
            tickets.add(new Ticket(1, basicPrice, id, seat));
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

    public Seat[] getFreeSeats() {
        return freeSeats;
    }

    public Seat[] getBookedSeats() {
        return bookedSeats;
    }
}
