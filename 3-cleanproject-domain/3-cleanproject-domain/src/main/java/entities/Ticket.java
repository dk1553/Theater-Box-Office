package entities;

import vo.Price;

import java.util.UUID;

public class Ticket {
    private String id;
    private String validationCode;
    private boolean booked;

    public Ticket() throws Exception {
        this.id = UUID.randomUUID().toString();
        this.validationCode = UUID.randomUUID().toString();
        this.booked = false;

    }

    public Ticket(String ticketID, Boolean booked, String validationCode) throws Exception {
        this.id = ticketID;
        this.validationCode = validationCode;
        this.booked = booked;
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
        return null;
    }


    public String getValidationCode() {
        return validationCode;
    }
    public String getEventID() {
        return "";
    }
    public String getSeat() {
        return "";
    }
    public String getEndOfValidity() {
        return "";
    }


}
