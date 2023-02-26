import java.util.Date;

public class Event {
    private Performance performance;
    private Date date;
    private Date time;
    private Hall hall;
    private Seat[] freeSeats;
    private Seat[] bookedSeats;

    public Event (Performance performance, Date date, Date time, Hall hall){
        this.performance=performance;
        this.date=date;
        this.time=time;
        this.hall=hall;
        this.freeSeats=hall.getSeats();
        this.bookedSeats=null;
    }


}
