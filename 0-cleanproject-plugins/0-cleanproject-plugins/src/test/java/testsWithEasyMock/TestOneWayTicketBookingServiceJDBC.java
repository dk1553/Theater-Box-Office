package testsWithEasyMock;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import businessObjects.*;
import org.junit.Before;
import org.junit.Test;
import repositories.TicketRepository;
import services.BookOneWayTicketDomainServiceJDBC;
import java.math.BigDecimal;


public class TestOneWayTicketBookingServiceJDBC {
    private OneWayTicket oneWayTicket;
    private TicketRepository ticketRepository;

    @Before
    public void setUp() throws Exception {
        ticketRepository=createMock(TicketRepository.class);
        oneWayTicket = new OneWayTicket("0", new Price(BigDecimal.valueOf(50)), new Seat("Kleine Halle", SeatType.BALCONY ));
        expect(ticketRepository.findTicketById("testID")).andReturn(oneWayTicket);
        ticketRepository.save(oneWayTicket);
        replay(ticketRepository);
    }

    @Test
    public void testStatusCheck() throws Exception {
        assertEquals(true,  new BookOneWayTicketDomainServiceJDBC().checkPossibilityToBook(oneWayTicket));
       oneWayTicket.setBooked();
        assertEquals(false,  new BookOneWayTicketDomainServiceJDBC().checkPossibilityToBook(oneWayTicket));
    }

    @Test
    public void testBooking() throws Exception {
        new BookOneWayTicketDomainServiceJDBC().bookTicket(ticketRepository, "testID");
        assertEquals(true,   oneWayTicket.isBooked());
    }
    @Test
    public void testFinding() throws Exception {
        assertEquals(oneWayTicket,   new BookOneWayTicketDomainServiceJDBC().findTicket(ticketRepository, "testID"));
    }

}

