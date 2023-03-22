package resources;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class EventResource {
    private String eventID;
    private String performance;
    private String date;
    private String time;
    private String hall;
    private String price;


    public EventResource( String eventID, String performance, String date, String time, String hall, String price) {
      this.eventID=eventID;
       this.date = date;
       this.performance=performance;
       this.hall=hall;
       this.time=time;
       this.price=price;
    }
    public EventResource( String performance, String date, String time, String hall, String price) {
        this.eventID= UUID.randomUUID().toString();
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
    public String getEventID() {
        return eventID;
    }
}
