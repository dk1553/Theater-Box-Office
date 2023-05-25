package TestVOs;

import org.junit.Test;
import vo.Price;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestPrice {
    @Test
    public void testRegularCreation() {
        try {
            Price testPrice1 = new Price("50");
            assertEquals(testPrice1.toString(), "50");
            Price testPrice2 = new Price("50.0");
            assertEquals(testPrice2.toString(), "50.0");
            Price testPrice3 = new Price("50.00");
            assertEquals(testPrice3.toString(), "50.0");
            Price testPrice4 = new Price(BigDecimal.valueOf(50));
            assertEquals(testPrice4.toString(), "50");
            Price testPrice5 = new Price(BigDecimal.valueOf(50.0));
            assertEquals(testPrice5.toString(), "50.0");
            Price testPrice6 = new Price(BigDecimal.valueOf(50.00));
            assertEquals(testPrice6.toString(), "50.0");
        } catch (Exception e) {
            assertTrue("Creation of price failed", false);
        }

    }
    @Test
    public void testFailCreation() {

        Boolean creationFalied = false;
        try {
            new Price("test");
        } catch (Exception e) {
            creationFalied = true;
        }
        assertTrue(creationFalied);
    }

}
