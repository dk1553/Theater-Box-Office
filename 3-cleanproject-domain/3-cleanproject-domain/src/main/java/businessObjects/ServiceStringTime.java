package businessObjects;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class ServiceStringTime {
    final  private String time;
    final  private String date;

    public ServiceStringTime(Date date, Date time) {
        this.time = validateTime(time);
        this.date = validateDate(date);

    }

    public ServiceStringTime(Date date) {
        this.time = "";
        this.date = validateDate(date);

    }

    private String validateTime(Date time) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
            return formatter.format(time);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String validateDate(Date date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            return formatter.format(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}

