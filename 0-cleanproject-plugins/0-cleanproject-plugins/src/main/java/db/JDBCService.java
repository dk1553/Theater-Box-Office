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
    static Connection connection;
    static Statement stmt;
    static ResultSet rs;
   final static String INSERT = "INSERT INTO ";
    final    static String VALUES = " VALUES (\'";
    final  static String COMMA = "\',\'";
    final   static String END_OF_COMMAND = "\');";
    final   static String SELECT_FROM = "SELECT * FROM ";
    final  static String UPDATE = "UPDATE ";
    final   static String SET = " SET ";
    final  static String WHERE = " WHERE ";


    public JDBCService() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(TheaterServiceApp.JDBC_SQLITE_DATABASE);
        connection.setAutoCommit(false);
        stmt = connection.createStatement();
        MessagePrinter.dbOpened();
    }

    public void close() throws SQLException {
        rs.close();
        stmt.close();
        connection.close();
        MessagePrinter.dbClosed();
    }

    public static List<PerformanceResource> getRepertoire() throws SQLException {
        List<PerformanceResource> performances = new ArrayList<>();
        rs = stmt.executeQuery(SELECT_FROM +"performances;");
        while (rs.next()) {
            String name = rs.getString("name");
            String description = rs.getString("description");
            performances.add(new PerformanceResource(name, description));
        }
        return performances;
    }

    public static List<EventResource> getTheaterProgram() throws Exception {
        List<EventResource> events = new ArrayList<>();
        rs = stmt.executeQuery(SELECT_FROM +"program;");
        while (rs.next()) {
            String eventID = rs.getString("eventID");
            String performanceName = rs.getString("performance");
            String hall = rs.getString("hall");
            BigDecimal price = rs.getBigDecimal("basicPrice");
            events.add(new EventResource(eventID, performanceName, rs.getString("date"), rs.getString("time"), hall, String.valueOf(price)));
        }

        return events;
    }

    public static void addPerformancesToDatabase(List<PerformanceResource> performanceList) throws SQLException {
        for (PerformanceResource performance : performanceList) {
            String sql = INSERT +"performances (name, description)" +
                    VALUES + performance.getName() + COMMA + performance.getDescription() + END_OF_COMMAND;
            stmt.executeUpdate(sql);
        }
        connection.commit();
        MessagePrinter.recordsCreated();
    }

    public void addEventsToDatabase(List<EventResource> events) throws SQLException {
        for (EventResource event : events) {
            String sql = INSERT +"program (performance, date, time, hall, basicPrice, eventID)" +
                    VALUES + event.getPerformance() + COMMA + event.getDate() + COMMA + event.getTime() + COMMA + event.getHall() + COMMA + event.getPrice() + COMMA + event.getEventID() + END_OF_COMMAND;
            stmt.executeUpdate(sql);
        }
        connection.commit();
        MessagePrinter.recordsCreated();
    }

    public List<TicketResource> getTickets() throws Exception {
        List<TicketResource> tickets = new ArrayList<>();
        rs = stmt.executeQuery(SELECT_FROM +"tickets;");

        while (rs.next()) {
            String ticketID = rs.getString("ticketID");
            String basicPrice = rs.getString("basicPrice");
            String eventID = rs.getString("eventID");
            String seat = rs.getString("seat");
            String validationCode = rs.getString("validationCode");
            boolean isBooked = false;
            if (rs.getInt("isBooked") == 1) {
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
            stmt.executeUpdate(sql);
        }
        connection.commit();
        MessagePrinter.recordsCreated();
    }



    public void buyTicket(TicketResource ticket) throws SQLException {
        String sql = UPDATE +"tickets"+ SET +"isBooked=" + 1 + WHERE +"ticketID=\'" + ticket.getId() + "\';";
        stmt.executeUpdate(sql);
        connection.commit();
        MessagePrinter.recordsCreated();
    }


}
