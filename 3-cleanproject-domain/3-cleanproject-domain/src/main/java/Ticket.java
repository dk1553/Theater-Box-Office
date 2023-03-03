import org.eclipse.jetty.util.DateCache;

import java.math.BigDecimal;
import java.util.function.Predicate;

public class Ticket {
    private int id;
    private Price price;
    private int eventID;
    private Seat seat;

    private boolean booked;

    public Ticket(int id, Price basicPrice, int eventID, Seat seat) throws Exception {
        this.id=id;
        this.eventID=eventID;
        this.seat=seat;

       ;
        this.price= new Price(basicPrice.getAmount().multiply(BigDecimal.valueOf(seat.getType().getPriceCoefficient())));
        this.booked=false;
    }

    public int getId() {
        return id;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked() {
        this.booked = true;
    }

    public Price getPrice() {
        return price;
    }


    public Seat getSeat() {
        return seat;
    }


}
