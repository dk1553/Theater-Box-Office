package TestBusinessObjects;

import entities.*;
import enums.SeatType;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import vo.Price;
import vo.ServiceTime;

import java.util.UUID;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestTickets {
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
    public void testYearTicket() throws Exception {
        String ticketID = UUID.randomUUID().toString();
        String validationCode = UUID.randomUUID().toString();
        Ticket yearTicket = new YearTicket(ticketID, price, true, validationCode, serviceTime.getDate());
        assertNotNull(yearTicket);
        assertEquals("12.05.2022", yearTicket.getEndOfValidity());
        assertEquals(ticketID, yearTicket.getId());
        assertEquals(validationCode, yearTicket.getValidationCode());
        assertEquals(true, yearTicket.isBooked());
        assertEquals(price, yearTicket.getPrice());
    }



    @Test
    public void testOneWayTicket() throws Exception {
        String ticketID = UUID.randomUUID().toString();
        String validationCode = UUID.randomUUID().toString();
        Ticket oneWayTicket = new OneWayTicket(ticketID, "testEvent",price, seat, true, validationCode);
        assertNotNull(oneWayTicket);
        assertEquals("testEvent", oneWayTicket.getEventID());
        assertEquals(ticketID, oneWayTicket.getId());
        assertEquals(validationCode, oneWayTicket.getValidationCode());
        assertEquals(true, oneWayTicket.isBooked());
        assertEquals(Double.parseDouble(new Price("60.0").toString()), Double.parseDouble(oneWayTicket.getPrice().toString()), 0 );
    }
}
