package businessObjects;

import java.math.BigDecimal;
import java.util.UUID;

public class Ticket {
    private String id;
    private Price price;
    private String eventID;
    private String seat;
    private String validationCode;
    private boolean booked;

    public Ticket(String eventID, Price basicPrice, Seat seat) {
        this.id = UUID.randomUUID().toString();
        this.validationCode = UUID.randomUUID().toString();
        this.eventID = eventID;
        this.seat = seat.getSeatNumber();
        this.booked = false;
        this.price = calculatePrice(basicPrice, seat.getType().getPriceCoefficient());
    }

    public Ticket(String ticketID, String eventID, Price basicPrice, Seat seat, Boolean booked, String validationCode) {
        this.id = ticketID;
        this.validationCode = validationCode;
        this.eventID = eventID;
        this.seat = seat.getSeatNumber();
        this.booked = booked;
        this.price = calculatePrice(basicPrice, seat.getType().getPriceCoefficient());
    }

    private Price calculatePrice(Price basicPrice, double priceCoefficient) {
        try {
            return new Price(basicPrice.getAmount().multiply(BigDecimal.valueOf(priceCoefficient)));
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


    public String getSeat() {
        return seat;
    }

    public String getEventID() {
        return eventID;
    }

    public String getValidationCode() {
        return validationCode;
    }


}
