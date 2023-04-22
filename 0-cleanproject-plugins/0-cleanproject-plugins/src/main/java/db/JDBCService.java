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
    static Connection c;
    static Statement stmt;
    static ResultSet rs;
    final static String insert="INSERT INTO ";
    final static String values=" VALUES (\'";
    final static String comma="\',\'";
    final static String endOfCommand="\');";
    final static String selectFrom="SELECT * FROM ";


    public JDBCService() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection(TheaterServiceApp.JDBC_SQLITE_DATABASE);
        c.setAutoCommit(false);
        stmt = c.createStatement();
        MessagePrinter.dbOpened();
    }

    public void close() throws SQLException {
        rs.close();
        stmt.close();
        c.close();
        MessagePrinter.dbClosed();
    }

    public static List<PerformanceResource> getRepertoire() throws SQLException {
        List<PerformanceResource> performances = new ArrayList<>();
        rs = stmt.executeQuery(selectFrom+"performances;");
        while (rs.next()) {
            String name = rs.getString("name");
            String description = rs.getString("description");
            performances.add(new PerformanceResource(name, description));
        }
        return performances;
    }

    public static List<EventResource> getTheaterProgram() throws Exception {
        List<EventResource> events = new ArrayList<>();
        rs = stmt.executeQuery(selectFrom+"program;");
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
            String sql = insert+"performances (name, description)" +
                    values + performance.getName() + comma + performance.getDescription() + endOfCommand;
            stmt.executeUpdate(sql);
        }
        c.commit();
        MessagePrinter.recordsCreated();
    }

    public void addEventsToDatabase(List<EventResource> events) throws SQLException {
        for (EventResource event : events) {
            String sql = insert+"program (performance, date, time, hall, basicPrice, eventID)" +
                    values + event.getPerformance() + comma + event.getDate() + comma + event.getTime() + comma + event.getHall() + comma + event.getPrice() + comma + event.getEventID() + endOfCommand;
            stmt.executeUpdate(sql);
        }
        c.commit();
        MessagePrinter.recordsCreated();
    }

    public List<TicketResource> getTickets() throws Exception {
        List<TicketResource> tickets = new ArrayList<>();
        rs = stmt.executeQuery(selectFrom+"tickets;");

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
            String sql = insert+"tickets (ticketID, basicPrice, eventID, seat, isBooked, validationCode)" +
                    values + ticket.getId() + comma + ticket.getPrice() + comma + ticket.getEventID() + comma + ticket.getSeat() + comma + status + comma + ticket.getValidationCode() + endOfCommand;
            stmt.executeUpdate(sql);
        }
        c.commit();
        MessagePrinter.recordsCreated();
    }

    final static  String update="UPDATE ";
    final static String set=" SET ";
    final static String where=" WHERE ";

    public void buyTicket(TicketResource ticket) throws SQLException {
        String sql = update+"tickets"+set+"isBooked=" + 1 + where+"ticketID=\'" + ticket.getId() + "\';";
        stmt.executeUpdate(sql);
        c.commit();
        MessagePrinter.recordsCreated();
    }


}
