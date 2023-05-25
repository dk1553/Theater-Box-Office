package TestVOs;

import org.junit.Test;
import vo.ServiceStringTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

public class TestServiceStringTime {


    @Test
    public void testServiceStringTime() throws ParseException {
        SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm");
        ServiceStringTime serviceStringTime = new ServiceStringTime(formatterDate.parse("12.05.2022"), formatterTime.parse("10:15"));

        assertEquals("12.05.2022", serviceStringTime.getDate());
        assertEquals("10:15", serviceStringTime.getTime());

    }


}
