package businessObjects;

import java.util.Date;

public class YearTicket extends Ticket{
    private  String validUntil;
    private Price price;
    public YearTicket(Price price, Date validUntil) throws Exception {
       this.price=price;
        this.validUntil = ValidationService.validateDate(validUntil);
    }

    public YearTicket(String ticketID, Price price, Boolean booked, String validationCode, Date validUntil) throws Exception {
        super(ticketID, booked, validationCode);
        this.validUntil = ValidationService.validateDate(validUntil);
        this.price=price;
    }
    @Override
    public Price getPrice() {
        return price;
    }
    @Override
    public String getEndOfValidity() {
        return validUntil;
    }



}
