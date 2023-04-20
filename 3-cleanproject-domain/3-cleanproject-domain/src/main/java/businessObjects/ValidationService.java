package businessObjects;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidationService {
    static String validateTime(Date time){
        String validatedTime=null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
            validatedTime = formatter.format(time);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return validatedTime;
    }

    static String validateDate(Date date){
        String validatedDate=null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            validatedDate = formatter.format(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return validatedDate;
    }
}

