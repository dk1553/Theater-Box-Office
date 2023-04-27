package resources;

import businessObjects.Currency;
public class TicketResource {
    private final String id;
    private final String basicPrice;
    private final String eventID;
    private final String seat;
    private final String validationCode;

    private final boolean booked;
    private final String currency;

    public TicketResource(String ticketID, String eventID, String basicPrice, String seat, Boolean booked, String validationCode) {
        this.validationCode = validationCode;
        this.id = ticketID;
        this.eventID = eventID;
        this.seat = seat;
        this.basicPrice = basicPrice;
        this.booked = booked;
        this.currency = Currency.EURO.getSymbol();
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
}

