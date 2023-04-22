package db;

import resources.EventResource;
import resources.PerformanceResource;
import resources.TicketResource;
import rest.TheaterServiceApp;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCService {
    static Connection C;
    static Statement STMT;
    static ResultSet RS;
    static String INSERT = "INSERT INTO ";
    static String VALUES = " VALUES (\'";
    static String COMMA = "\',\'";
    static String END_OF_COMMAND = "\');";
    static String SELECT_FROM = "SELECT * FROM ";
    static String UPDATE = "UPDATE ";
    static String SET = " SET ";
    static String WHERE = " WHERE ";


    public JDBCService() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        C = DriverManager.getConnection(TheaterServiceApp.JDBC_SQLITE_DATABASE);
        C.setAutoCommit(false);
        STMT = C.createStatement();
        MessagePrinter.dbOpened();
    }

    public void close() throws SQLException {
        RS.close();
        STMT.close();
        C.close();
        MessagePrinter.dbClosed();
    }

    public static List<PerformanceResource> getRepertoire() throws SQLException {
        List<PerformanceResource> performances = new ArrayList<>();
        RS = STMT.executeQuery(SELECT_FROM +"performances;");
        while (RS.next()) {
            String name = RS.getString("name");
            String description = RS.getString("description");
            performances.add(new PerformanceResource(name, description));
        }
        return performances;
    }

    public static List<EventResource> getTheaterProgram() throws Exception {
        List<EventResource> events = new ArrayList<>();
        RS = STMT.executeQuery(SELECT_FROM +"program;");
        while (RS.next()) {
            String eventID = RS.getString("eventID");
            String performanceName = RS.getString("performance");
            String hall = RS.getString("hall");
            BigDecimal price = RS.getBigDecimal("basicPrice");
            events.add(new EventResource(eventID, performanceName, RS.getString("date"), RS.getString("time"), hall, String.valueOf(price)));
        }

        return events;
    }

    public static void addPerformancesToDatabase(List<PerformanceResource> performanceList) throws SQLException {
        for (PerformanceResource performance : performanceList) {
            String sql = INSERT +"performances (name, description)" +
                    VALUES + performance.getName() + COMMA + performance.getDescription() + END_OF_COMMAND;
            STMT.executeUpdate(sql);
        }
        C.commit();
        MessagePrinter.recordsCreated();
    }

    public void addEventsToDatabase(List<EventResource> events) throws SQLException {
        for (EventResource event : events) {
            String sql = INSERT +"program (performance, date, time, hall, basicPrice, eventID)" +
                    VALUES + event.getPerformance() + COMMA + event.getDate() + COMMA + event.getTime() + COMMA + event.getHall() + COMMA + event.getPrice() + COMMA + event.getEventID() + END_OF_COMMAND;
            STMT.executeUpdate(sql);
        }
        C.commit();
        MessagePrinter.recordsCreated();
    }

    public List<TicketResource> getTickets() throws Exception {
        List<TicketResource> tickets = new ArrayList<>();
        RS = STMT.executeQuery(SELECT_FROM +"tickets;");

        while (RS.next()) {
            String ticketID = RS.getString("ticketID");
            String basicPrice = RS.getString("basicPrice");
            String eventID = RS.getString("eventID");
            String seat = RS.getString("seat");
            String validationCode = RS.getString("validationCode");
            boolean isBooked = false;
            if (RS.getInt("isBooked") == 1) {
                isBooked = true;
            }
            tickets.add(new TicketResource(ticketID, eventID, basicPrice, seat, isBooked, validationCode));
        }

        return tickets;
    }

    public void addTicketsToDatabase(List<TicketResource> tickets) throws SQLException {
        for (TicketResource ticket : tickets) {
            int status = 0;
            if (ticket.isBooked()) {
                status = 1;
            }
            String sql = INSERT +"tickets (ticketID, basicPrice, eventID, seat, isBooked, validationCode)" +
                    VALUES + ticket.getId() + COMMA + ticket.getPrice() + COMMA + ticket.getEventID() + COMMA + ticket.getSeat() + COMMA + status + COMMA + ticket.getValidationCode() + END_OF_COMMAND;
            STMT.executeUpdate(sql);
        }
        C.commit();
        MessagePrinter.recordsCreated();
    }



    public void buyTicket(TicketResource ticket) throws SQLException {
        String sql = UPDATE +"tickets"+ SET +"isBooked=" + 1 + WHERE +"ticketID=\'" + ticket.getId() + "\';";
        STMT.executeUpdate(sql);
        C.commit();
        MessagePrinter.recordsCreated();
    }


}
