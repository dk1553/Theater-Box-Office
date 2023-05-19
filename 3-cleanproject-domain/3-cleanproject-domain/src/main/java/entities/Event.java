package entities;

import vo.Price;
import vo.ServiceStringTime;

import java.util.Date;
import java.util.UUID;

public class Event {
    private final Performance performance;
    private final String id;
    private final String date;
    private final String time;
    private final String hallName;
    private final Price basicPrice;





    public Event(Performance performance, Date date, Date time, String hallName, Price basicPrice) throws Exception {
        this.id = UUID.randomUUID().toString();
        this.performance = performance;
        ServiceStringTime serviceStringTime = new ServiceStringTime(date, time);
        this.date = serviceStringTime.getDate();
        this.time = serviceStringTime.getTime();
        this.basicPrice = basicPrice;
        this.hallName = hallName;
    }

    public Event(String id, Performance performance, Date date, Date time, String hallName, Price basicPrice) throws Exception {
        this.id = id;
        this.performance = performance;
        ServiceStringTime serviceStringTime = new ServiceStringTime(date, time);
        this.date = serviceStringTime.getDate();
        this.time = serviceStringTime.getTime();
        this.basicPrice = basicPrice;
        this.hallName = hallName;
    }

    public Performance getPerformance() {
        return performance;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getHallName() {
        return hallName;
    }

    public Price getBasicPrice() {
        return basicPrice;
    }


}
