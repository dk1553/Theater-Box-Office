import businessObjects.Event;
import businessObjects.Performance;
import businessObjects.Price;
import businessObjects.TheaterBuilding;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class TestBusinessObjectsCreation {
    @org.junit.Test
    public void testEventCreation() {
        TheaterBuilding theaterBuilding= new TheaterBuilding();
        try {
            Event testEvent = new Event(new Performance("testPerformance", "testDescription"), new Date(),new Date(), theaterBuilding.findHallByName("Kleine halle"), new Price("50"));
            assertNotNull(testEvent);
        } catch (Exception e) {
            assertTrue("Creation of event failed", false);
        }
    }
    @org.junit.Test
    public void testPriceCreation() {
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

        Boolean creationFalied = false;
        try {
            Price testPrice7 = new Price("test");
        } catch (Exception e) {
            creationFalied=true;
        }
        assertTrue(creationFalied);
    }

}
