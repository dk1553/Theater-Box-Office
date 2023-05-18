package businessObjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ServiceTime {
    private final Date date;
    private final Date time;
    public ServiceTime(String date, String time) throws ParseException {
        this.date = parseDate(date);
        this.time = parseTime(time);
    }
     public  ServiceTime(String date) throws ParseException {
        this.date = parseDate(date);
        this.time = null;
    }

    private Date parseDate(String date) throws ParseException {
        SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.yyyy");
        return formatterDate.parse(date);

    }
    private Date parseTime(String time) throws ParseException {
        SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm");
        return formatterTime.parse(time);

    }

    public Date getDate() {
        return date;
    }

    public Date getTime() {
        return time;
    }
}
