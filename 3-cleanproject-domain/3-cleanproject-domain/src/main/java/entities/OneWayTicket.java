package entities;

import vo.Price;

import java.math.BigDecimal;

public class OneWayTicket extends Ticket{
    private String eventID;
    private String seat;
    private Price price;


    public OneWayTicket(String eventID, Price basicPrice, Seat seat) throws Exception {
        super();
        this.eventID = eventID;
        this.seat = seat.getSeatNumber();
        this.price = calculatePrice(basicPrice, seat.getType().getPriceCoefficient());
    }

    public OneWayTicket(String ticketID, String eventID, Price basicPrice, Seat seat, Boolean booked, String validationCode) throws Exception {
        super(ticketID, booked, validationCode);
        this.eventID = eventID;
        this.seat = seat.getSeatNumber();
        this.price = calculatePrice(basicPrice, seat.getType().getPriceCoefficient());
    }
    private Price calculatePrice(Price basicPrice, double priceCoefficient) {
        try {
            return new Price(basicPrice.getAmount().multiply(BigDecimal.valueOf(priceCoefficient)));
        } catch (Exception e) {
            return new Price();
        }
    }
    @Override
    public String getEventID() {
        return eventID;
    }
    @Override
    public String getSeat() {
        return seat;
    }
    @Override
    public Price getPrice(){
        return price;
    }
}
