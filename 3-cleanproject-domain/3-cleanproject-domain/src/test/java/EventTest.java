/*import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import businessObjects.Event;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import businessObjects.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;

@PrepareForTest(Hall.class)
public class EventTest {

    private Performance performance;
    @Mock
    private Hall hall;
    @Mock
    private Price price;

    private Date date;
    private Date time;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        performance= new Performance("p", "d");
        date = new Date();
        time = new Date();
    }

    @Test
    public void testConstructor() throws Exception {
        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(mock(Ticket.class));
        String id = UUID.randomUUID().toString();
        Event event = new Event(id, performance, date, time, hall.getHallName(), price, tickets);
        assertEquals(id, event.getId());
        assertEquals(performance, event.getPerformance());
        assertEquals(date, event.getDate());
        assertEquals(time, event.getTime());
        assertEquals(price, event.getBasicPrice());
        assertEquals(tickets, event.getTickets());
        assertEquals(hall.getHallName(), event.getHallName());
    }

    @Test
    public void testSetHall() throws Exception {
        Seat [] seats= new Seat[2];
        seats[0]=mock(Seat.class);
        seats[1]=mock(Seat.class);

        whenNew(Hall.class).withNoArguments().thenReturn(hall);
        when(hall.getSeats()).thenReturn(seats);

        Event event = new Event(performance, date, time, hall, price);
        event.setHall(hall);

        assertEquals(hall, event.getHall());
        assertEquals(seats.length, event.getTickets().size());

        for (int i = 0; i < seats.length; i++) {
            assertEquals(event.getId(), event.getTickets().get(i).getEventID());
            assertEquals(price, event.getTickets().get(i).getPrice());
            assertEquals(seats[i], event.getTickets().get(i).getSeat());
        }
    }
}*/