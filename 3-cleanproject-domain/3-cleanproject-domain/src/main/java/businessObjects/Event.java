package businessObjects;

import repositories.SeatRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Event {
    private final Performance performance;
    private final String id;
    private final String date;
    private final String time;
    private final String hallName;
    private final Price basicPrice;
    private List<Ticket> tickets;



    public List<Ticket> getTickets() {
        return tickets;
    }

    public Event (Performance performance, Date date, Date time, String hallName, Price basicPrice, SeatRepository seatRepository) throws Exception {
        this.id= UUID.randomUUID().toString();
        this.performance=performance;
        this.date=ValidationService.validateDate(date);
        this.time=ValidationService.validateTime(time);
        this.basicPrice=basicPrice;
        tickets=new ArrayList<>();
        for (Seat seat:seatRepository.findSeatsByHallName(hallName)){
            tickets.add(new Ticket(id,basicPrice, seat));
        }
        this.hallName= hallName;

    }

    public Event (String id, Performance performance, Date date, Date time, String hallName, Price basicPrice, ArrayList <Ticket> tickets) throws Exception {
        this.id=id;
        this.performance=performance;
        this.date=ValidationService.validateDate(date);
        this.time=ValidationService.validateTime(time);
        this.tickets=tickets;
        this.basicPrice=basicPrice;
        this.hallName=hallName;

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

    public Price getBasicPrice(){
        return basicPrice;
    }
    

}
