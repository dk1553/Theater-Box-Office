package TestBusinessObjects;

import entities.Performance;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestPerformance {
    @Test
    public void testPerformance() throws Exception {
        Performance newPerformance = new Performance("testPerformance", "testDescription");
        assertNotNull(newPerformance);
        assertEquals("testPerformance",newPerformance.getName());
        assertEquals("testDescription",newPerformance.getDescription());
    }
}
