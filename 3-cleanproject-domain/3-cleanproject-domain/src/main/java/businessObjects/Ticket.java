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

    public Ticket(String eventID, Price basicPrice, Seat seat) {
        this.id= UUID.randomUUID().toString();
        this.validationCode=UUID.randomUUID().toString();
        this.eventID=eventID;
        this.seat=seat;
        this.booked=false;
        this.price= buildPrice(basicPrice);
    }

    public Ticket(String ticketID, String eventID, Price basicPrice, Seat seat, Boolean booked, String validationCode){
        this.id=ticketID;
        this.validationCode=validationCode;
        this.eventID=eventID;
        this.seat=seat;
        this.booked=booked;
        this.price= buildPrice(basicPrice);
    }
    private Price buildPrice(Price basicPrice){
        try {
            return new Price(basicPrice.getAmount().multiply(BigDecimal.valueOf(seat.getType().getPriceCoefficient())));
        } catch (Exception e) {
            return new Price();
        }
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
