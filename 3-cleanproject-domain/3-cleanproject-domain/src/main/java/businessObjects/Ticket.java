package businessObjects;

import java.math.BigDecimal;
import java.util.UUID;

public class Ticket {
    private String id;
    private Price price;
    private String eventID;
    private Seat seat;
    private String validationCode;

    private boolean booked;

    public Ticket(String eventID, Price basicPrice, Seat seat) throws Exception {
        this.id= UUID.randomUUID().toString();
        this.validationCode=UUID.randomUUID().toString();
        this.eventID=eventID;
        this.seat=seat;
        this.price= new Price(basicPrice.getAmount().multiply(BigDecimal.valueOf(seat.getType().getPriceCoefficient())));
        this.booked=false;
    }

    public Ticket(String ticketID, String eventID, Price basicPrice, Seat seat, Boolean booked, String validationCode) throws Exception {
        this.id=ticketID;
        this.validationCode=validationCode;
        this.eventID=eventID;
        this.seat=seat;
        this.price= new Price(basicPrice.getAmount().multiply(BigDecimal.valueOf(seat.getType().getPriceCoefficient())));
        this.booked=booked;
    }

    public String getId() {
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

    public  String getEventID(){ return  eventID;}
    public  String getValidationCode(){ return  validationCode;}


}
