package businessObjects;

import java.util.Calendar;
import java.util.Date;

public class YearTicket extends Ticket {
    private String validUntil;
    private Price price;

    public YearTicket(Price price) throws Exception {
        super();
        this.price = price;
        this.validUntil = calculateEndOfValidity();
    }

    public YearTicket(String ticketID, Price price, Boolean booked, String validationCode, Date validUntil) throws Exception {
        super(ticketID, booked, validationCode);
        this.validUntil = ValidationService.validateDate(validUntil);
        this.price = price;
    }

    private String calculateEndOfValidity() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 365);
        return ValidationService.validateDate(c.getTime());
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
