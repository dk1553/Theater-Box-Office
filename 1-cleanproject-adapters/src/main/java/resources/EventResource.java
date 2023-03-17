package resources;

import java.math.BigDecimal;
import java.util.Date;

public class EventResource {
    private String performance;
    private String date;
    private String time;
    private String hall;
    private String price;


    public EventResource(String performance, String date, String time, String hall, String price) {
       this.date = date;
       this.performance=performance;
       this.hall=hall;
       this.time=time;
       this.price=price;
    }

    public String getPerformance() {
        return performance;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getHall() {
        return hall;
    }

    public String getPrice() {
        return price;
    }
}
