package resources;

import businessObjects.Currency;
import businessObjects.Price;
import businessObjects.Seat;

import java.math.BigDecimal;
import java.util.UUID;

public class TicketResource {
    private String id;
    private String basicPrice;
    private String eventID;
    private String  seat;
    private String  validationCode;

    private boolean booked;
    private String currency;

    public TicketResource(String ticketID, String eventID, String basicPrice, String seat, Boolean booked, String validationCode) throws Exception {
        this.validationCode=validationCode;
        this.id=ticketID;
        this.eventID=eventID;
        this.seat=seat;
        this.basicPrice= basicPrice;
        this.booked=booked;
        this.currency= Currency.EURO.getSymbol();
    }

    public String getId() {
        return id;
    }
    public String getCurrency(){
        return currency;
    }

    public boolean isBooked() {
        return booked;
    }


    public String getPrice() {
        return basicPrice;
    }


    public String getSeat() {
        return seat;
    }

    public  String getEventID(){ return  eventID;}
    public  String getValidationCode(){ return  validationCode;}
}

