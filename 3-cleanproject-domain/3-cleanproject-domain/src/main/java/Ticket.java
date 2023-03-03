import org.eclipse.jetty.util.DateCache;

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
        basicPrice.setAmount(Math.round(seat.getType().getPriceCoefficient()*basicPrice.getAmount()));
        this.price=basicPrice;
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


    public int getSeatNumber() {
        return seat.getSeatNumber();
    }
}
