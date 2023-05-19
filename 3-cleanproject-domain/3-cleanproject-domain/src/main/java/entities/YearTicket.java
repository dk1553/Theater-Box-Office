package entities;

import vo.Price;
import vo.ServiceStringTime;

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
        this.validUntil = new ServiceStringTime(validUntil).getDate();
        this.price = price;
    }

    private String calculateEndOfValidity() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 365);
        return  new ServiceStringTime(c.getTime()).getDate();
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
