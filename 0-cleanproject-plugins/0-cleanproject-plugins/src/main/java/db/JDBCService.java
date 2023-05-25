package db;

import resources.EventResource;
import resources.PerformanceResource;
import resources.TicketResource;
import rest.TheaterBoxOfficeApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCService {
    static Connection connection;
    static Statement stmt;
    static ResultSet rs;
    final static String INSERT = "INSERT INTO ";
    final static String VALUES = " VALUES (\'";
    final static String COMMA = "\',\'";
    final static String END_OF_COMMAND = "\');";
    final static String SELECT_FROM = "SELECT * FROM ";
    final static String UPDATE = "UPDATE ";
    final static String SET = " SET ";
    final static String WHERE = " WHERE ";

    private JDBCService() {
        throw new IllegalStateException("Utility class");
    }

    private static void openConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(TheaterBoxOfficeApp.JDBC_SQLITE_DATABASE);
        connection.setAutoCommit(false);
        stmt = connection.createStatement();
        MessagePrinter.dbOpened();
    }

    private static void closeConnection() throws SQLException {
        rs.close();
        stmt.close();
        connection.close();
        MessagePrinter.dbClosed();
    }

    public static List<PerformanceResource> getRepertoire() throws SQLException, ClassNotFoundException {
        openConnection();
        List<PerformanceResource> performances = new ArrayList<>();
        rs = stmt.executeQuery(SELECT_FROM + "performances;");
        while (rs.next()) {
            String name = rs.getString("name");
            String description = rs.getString("description");
            performances.add(new PerformanceResource(name, description));
        }

        closeConnection();
        return performances;
    }

    public static List<EventResource> getTheaterProgram() throws Exception {
        openConnection();
        List<EventResource> events = new ArrayList<>();
        rs = stmt.executeQuery(SELECT_FROM + "program;");
        while (rs.next()) {
            String eventID = rs.getString("eventID");
            String performanceName = rs.getString("performance");
            String hall = rs.getString("hall");
            String price = rs.getString("basicPrice");
            events.add(new EventResource(eventID, performanceName, rs.getString("date"), rs.getString("time"), hall, price, null));
        }
        closeConnection();

        return events;
    }

    public static void addPerformancesToDatabase(List<PerformanceResource> performanceList) throws SQLException, ClassNotFoundException {
        openConnection();
        for (PerformanceResource performance : performanceList) {
            String sql = INSERT + "performances (name, description)" +
                    VALUES + performance.getName() + COMMA + performance.getDescription() + END_OF_COMMAND;
            stmt.executeUpdate(sql);
        }
        connection.commit();
        MessagePrinter.recordsCreated();
        closeConnection();
    }

    public static void addEventsToDatabase(List<EventResource> events) throws SQLException, ClassNotFoundException {
        openConnection();
        for (EventResource event : events) {
            String sql = INSERT + "program (performance, date, time, hall, basicPrice, eventID)" +
                    VALUES + event.getPerformance() + COMMA + event.getDate() + COMMA + event.getTime() + COMMA + event.getHall() + COMMA + event.getPrice() + COMMA + event.getEventID() + END_OF_COMMAND;
            stmt.executeUpdate(sql);
        }
        connection.commit();
        MessagePrinter.recordsCreated();
        closeConnection();
    }

    public static List<TicketResource> getAllTickets() throws Exception {
        List<TicketResource> tickets = new ArrayList<>();
        tickets.addAll(getOneWayTickets());
        tickets.addAll(getYearTickets());
        return tickets;
    }

    private static List<TicketResource> getOneWayTickets() throws Exception {
        openConnection();
        List<TicketResource> tickets = new ArrayList<>();
        rs = stmt.executeQuery(SELECT_FROM + "oneWayTickets;");

        while (rs.next()) {
            String ticketID = rs.getString("ticketID");
            String basicPrice = rs.getString("price");
            String eventID = rs.getString("eventID");
            String seat = rs.getString("seat");
            String validationCode = rs.getString("validationCode");
            boolean isBooked = rs.getBoolean("isBooked");
            tickets.add(new TicketResource(false, ticketID, eventID, basicPrice, seat, isBooked, validationCode, ""));
        }
        closeConnection();
        return tickets;
    }

    private static List<TicketResource> getYearTickets() throws Exception {
        openConnection();
        List<TicketResource> tickets = new ArrayList<>();
        rs = stmt.executeQuery(SELECT_FROM + "yearTickets;");

        while (rs.next()) {
            String ticketID = rs.getString("ticketID");
            String basicPrice = rs.getString("price");
            String validationCode = rs.getString("validationCode");
            String endOfValidity = rs.getString("validUntil");
            boolean isBooked = rs.getBoolean("isBooked");
            tickets.add(new TicketResource(true, ticketID, "", basicPrice, "", isBooked, validationCode, endOfValidity));
        }
        closeConnection();
        return tickets;
    }


    public static void addTicketsToDatabase(List<TicketResource> tickets) throws SQLException, ClassNotFoundException {
        openConnection();
        for (TicketResource ticket : tickets) {
            String sql;
            if (ticket.isYearTicket()) {
                sql = INSERT + "yearTickets (ticketID, price, isBooked, validationCode, validUntil)" +
                        VALUES + ticket.getId() + COMMA + ticket.getPrice() + COMMA + ticket.isBooked() + COMMA + ticket.getValidationCode() + COMMA + ticket.getEndOfValidity() + END_OF_COMMAND;
            } else {
                sql = INSERT + "oneWayTickets (ticketID, price, eventID, seat, isBooked, validationCode)" +
                        VALUES + ticket.getId() + COMMA + ticket.getPrice() + COMMA + ticket.getEventID() + COMMA + ticket.getSeat() + COMMA + ticket.isBooked() + COMMA + ticket.getValidationCode() + END_OF_COMMAND;
            }
            stmt.executeUpdate(sql);
        }
        connection.commit();
        MessagePrinter.recordsCreated();
        closeConnection();
    }


    public static void buyOneWayTicket(TicketResource ticket) throws SQLException, ClassNotFoundException {
        openConnection();
        String sql = UPDATE + "oneWayTickets" + SET + "isBooked=true" + WHERE + "ticketID=\'" + ticket.getId() + "\';";
        stmt.executeUpdate(sql);
        connection.commit();
        MessagePrinter.recordsCreated();
        closeConnection();
    }


}
