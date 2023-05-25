package testImplementationOfServices;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import entities.*;
import enums.SeatType;
import org.junit.Before;
import org.junit.Test;
import repositories.TicketRepository;
import services.BookOneWayTicketDomainServiceJDBC;
import vo.Price;


public class TestOneWayTicketBookingServiceJDBC {
    private OneWayTicket oneWayTicket;
    private TicketRepository ticketRepository;

    @Before
    public void setUp() throws Exception {
        ticketRepository=createMock(TicketRepository.class);
        oneWayTicket = new OneWayTicket("0", new Price("50"), new Seat("Kleine Halle", SeatType.BALCONY ));
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

