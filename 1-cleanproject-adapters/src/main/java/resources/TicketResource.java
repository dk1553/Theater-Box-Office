package resources;

import businessObjects.Currency;
public class TicketResource {
    private final String id;
    private final String basicPrice;
    private final String eventID;
    private final String seat;
    private final String validationCode;
    private final Boolean isYearTicket;

    private final boolean booked;
    private final String currency;
    public String getEndOfValidity;

    public TicketResource(Boolean isYearTicket, String ticketID, String eventID, String basicPrice, String seat, Boolean booked, String validationCode,  String getEndOfValidity) {
        this.isYearTicket=isYearTicket;
        this.validationCode = validationCode;
        this.id = ticketID;
        this.eventID = eventID;
        this.seat = seat;
        this.basicPrice = basicPrice;
        this.booked = booked;
        this.currency = Currency.EURO.getSymbol();
        this.getEndOfValidity=getEndOfValidity;


    }


    public String getId() {
        return id;
    }

    public String getCurrency() {
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

    public String getEventID() {
        return eventID;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public String getEndOfValidity() {
        return getEndOfValidity;
    }
    public Boolean isYearTicket(){
        return isYearTicket;
    }
}

