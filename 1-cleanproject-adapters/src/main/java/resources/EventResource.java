package resources;

import java.math.BigDecimal;
import java.util.Date;

public class EventResource {
    private String performance;
    private Date data;
    private Date time;
    private String hall;
    private BigDecimal price;


    public EventResource(String performance, Date data, Date time, String hall, BigDecimal price) {
       this.data=data;
       this.performance=performance;
       this.hall=hall;
       this.time=time;
       this.price=price;
    }

    public String getPerformance() {
        return performance;
    }

    public Date getData() {
        return data;
    }

    public Date getTime() {
        return time;
    }

    public String getHall() {
        return hall;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
