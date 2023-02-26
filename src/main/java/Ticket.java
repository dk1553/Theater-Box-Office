import org.eclipse.jetty.util.DateCache;

public class Ticket {
    private int id;
    private Price price;
    private Event event;
    private Seat seat;

    public Ticket(int id, Price price, Event event, Seat seat){
        this.id=id;
        this.price=price;
        this.event=event;
        this.seat=seat;
    }

    public int getId() {
        return id;
    }

    public Price getPrice() {
        return price;
    }

    public Event getEvent() {
        return event;
    }

    public Seat getSeatNumber() {
        return seat;
    }
}
