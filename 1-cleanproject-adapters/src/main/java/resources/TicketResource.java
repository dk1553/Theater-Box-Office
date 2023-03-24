package resources;

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

    public TicketResource(String eventID, String basicPrice, String seat) throws Exception {
        this.id= UUID.randomUUID().toString();
        this.validationCode=UUID.randomUUID().toString();
        this.eventID=eventID;
        this.seat=seat;
        this.basicPrice= basicPrice;
        this.booked=false;
    }

    public TicketResource(String ticketID, String eventID, String basicPrice, String seat, Boolean booked, String validationCode) throws Exception {
        this.validationCode=validationCode;
        this.id=ticketID;
        this.eventID=eventID;
        this.seat=seat;
        this.basicPrice= basicPrice;
        this.booked=booked;
    }

    public String getId() {
        return id;
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

