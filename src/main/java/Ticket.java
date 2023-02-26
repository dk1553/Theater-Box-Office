import org.eclipse.jetty.util.DateCache;

public class Ticket {
    private int id;
    private Price price;
    private Event event;
    private int seatNumber;

    public Ticket(int id, Price price, Event event,int seatNumber){
        this.id=id;
        this.price=price;
        this.event=event;
        this.seatNumber=seatNumber;
    }
}
