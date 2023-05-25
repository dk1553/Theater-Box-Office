package TestVOs;

import org.junit.Test;
import vo.ServiceTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

public class TestServiceTime {
    @Test
    public void testServiceTime() throws ParseException {

        ServiceTime serviceTime = new ServiceTime("12.05.2022", "10:15");
        SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm");
        assertEquals(formatterDate.parse("12.05.2022"), serviceTime.getDate());
        assertEquals(formatterTime.parse("10:15"), serviceTime.getTime());

    }
}
