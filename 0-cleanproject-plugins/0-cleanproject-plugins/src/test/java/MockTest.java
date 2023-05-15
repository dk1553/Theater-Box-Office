import businessObjects.*;
import db.JDBCService;
import mapper.PerformanceMapper;
import mapper.PerformanceResourceMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import persistence.HallRepositoryImplementation;
import persistence.jdbc.PerformanceRepositoryJDBC;
import repositories.PerformanceRepository;
import resources.PerformanceResource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.when;

import resources.TicketResource;


public class MockTest {

    @Before
    public void init() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testEvent() {
        HallRepositoryImplementation hallRepositoryImplementation = new HallRepositoryImplementation();
        Performance performance = Mockito.mock(Performance.class);

        try {
            Event testEvent = new Event(performance, new Date(),new Date(), "Kleine halle", new Price("50"));
            Assert.assertNotNull(testEvent);
            String expected="testPerformance";
            when(performance.getName()).thenReturn(expected);
            String actual = testEvent.getPerformance().getName();
            Assert.assertEquals(expected, actual);
        } catch (Exception e) {
            Assert.assertTrue("Creation of event failed", false);
        }
    }

    @Test
    public void testPerformanceJDBC() throws Exception {
        PerformanceRepository performanceRepositoryJDBC= new PerformanceRepositoryJDBC();

        JDBCService jdbcService;
        jdbcService= Mockito.mock(JDBCService.class);
       List<PerformanceResource> performances= new ArrayList<>();
        performances.add(new PerformanceResource("first", "dF"));
        performances.add(new PerformanceResource("second", "dS"));
        performances.add(new PerformanceResource("third", "dT"));
        PerformanceMapper performanceMapper= new PerformanceMapper();
        PerformanceResourceMapper performanceResourceMapper = new PerformanceResourceMapper();

        when(jdbcService.getRepertoire()).thenReturn(performances);
        performanceRepositoryJDBC.addPerformances(performanceMapper.map(jdbcService.getRepertoire()));


       List <PerformanceResource> actual =performanceResourceMapper.map(performanceRepositoryJDBC.findAllPerformances());
        List <PerformanceResource> expected =performances;
            Assert.assertEquals(expected, actual);

    }
    @Test
    public void testTicket() {
        HallRepositoryImplementation hallRepositoryImplementation = new HallRepositoryImplementation();
        TicketResource ticketResource= Mockito.mock(TicketResource.class);
        Seat seat = Mockito.mock(Seat.class);

        try {
            Ticket testTicket = new OneWayTicket("eventID", new Price("50"), seat);
            Assert.assertNotNull(testTicket);
            String expected="testPerformance";
           // when(seat.getSeatID()).thenReturn(expected);
        //    String actual = testEvent.getPerformance().getName();
          //  Assert.assertEquals(expected, actual);
        } catch (Exception e) {
            Assert.assertTrue("Creation of event failed", false);
        }
    }
}
