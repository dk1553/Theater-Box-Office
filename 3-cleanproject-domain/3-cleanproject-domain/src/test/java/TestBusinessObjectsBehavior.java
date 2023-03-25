import businessObjects.TheaterBuilding;
import junit.framework.TestSuite;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestBusinessObjectsBehavior {
    @org.junit.Test
    public void testFindHall(){
        TheaterBuilding theaterBuilding= new TheaterBuilding();
        assertNotNull(theaterBuilding.findHallByName("Kleine Halle"));
        assertNotNull(theaterBuilding.findHallByName("Große Halle"));
        assertNull(theaterBuilding.findHallByName("Irgendeine Halle"));

    }
}
