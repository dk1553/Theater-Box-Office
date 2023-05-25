package TestBusinessObjects;

import entities.Event;
import entities.Performance;
import entities.Seat;
import enums.SeatType;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import vo.Price;
import vo.ServiceTime;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestEvent {
    private Price price;
    private Performance performance;
    private ServiceTime serviceTime;
    private Seat seat;
    @Before
    public void setUp() throws Exception {
        price= new Price("50");
        serviceTime= new ServiceTime("12.05.2022", "10:15");
        performance= EasyMock.createMock(Performance.class);
        EasyMock.expect(performance.getName()).andReturn("testPerformance");
        EasyMock.expect(performance.getDescription()).andReturn("testDescription");
        EasyMock.replay(performance);
        seat= EasyMock.createMock(Seat.class);
        EasyMock.expect(seat.getSeatNumber()).andReturn("5");
        EasyMock.expect(seat.getType()).andReturn(SeatType.MEZZANINE_LEFT);
        EasyMock.replay(seat);
    }
    @Test
    public void testEvent() throws Exception {
        Event testEvent = new Event(performance,serviceTime.getDate(),serviceTime.getTime(), "Kleine halle", price);
        assertNotNull(testEvent);
        assertEquals("Kleine halle",testEvent.getHallName());
        assertEquals("testPerformance",testEvent.getPerformance());
        assertEquals(price,testEvent.getBasicPrice());
        assertEquals("12.05.2022",testEvent.getDate());
        assertEquals("10:15",testEvent.getTime());
    }
}
