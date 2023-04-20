package db;

import resources.EventResource;
import resources.PerformanceResource;
import resources.TicketResource;
import rest.TheaterServiceApp;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JDBCService {
    static Connection c;
    static Statement stmt;
    static   ResultSet rs;

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
    public static ArrayList <PerformanceResource> getRepertoire() throws SQLException {
        ArrayList <PerformanceResource> performances=new ArrayList<>();
            rs = stmt.executeQuery( "SELECT * FROM performances;" );
            while ( rs.next() ) {
                String  name = rs.getString("name");
                String  description = rs.getString("description");
                performances.add(new PerformanceResource(name, description));
            }
        return performances;
    }
    public static ArrayList <EventResource> getTheaterProgram() throws Exception {
        ArrayList <EventResource> events=new ArrayList<>();
        rs = stmt.executeQuery( "SELECT * FROM program;" );
        while ( rs.next() ) {
            String  eventID = rs.getString("eventID");
            String  performanceName = rs.getString("performance");
            SimpleDateFormat simpleDateFormatDate= new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat simpleDateTime= new SimpleDateFormat("hhh:mm");
            Date data =simpleDateFormatDate.parse(rs.getString("date"));
            Date  time =simpleDateTime.parse(rs.getString("time"));
            String  hall = rs.getString("hall");
            BigDecimal price = rs.getBigDecimal("basicPrice");
            events.add(new EventResource(eventID, performanceName,rs.getString("date"),rs.getString("time"), hall, String.valueOf(price)));
        }

        return events;
    }

    public static  void addPerformancesToDatabase(ArrayList<PerformanceResource> performanceList) throws SQLException {
            for (PerformanceResource performance:performanceList){
                String sql = "INSERT INTO performances (name, description) " +
                        "VALUES (\'"+performance.getName()+"\',\'"+performance.getDescription()+"\');";
                stmt.executeUpdate(sql);
            }
            c.commit();
            MessagePrinter.recordsCreated();
    }

    public void addEventsToDatabase(ArrayList<EventResource> events) throws SQLException {
        for (EventResource event:events){
            String sql = "INSERT INTO program (performance, date, time, hall, basicPrice, eventID) " +
                    "VALUES (\'"+event.getPerformance()+"\',\'"+event.getDate()+"\',\'"+event.getTime()+"\',\'"+event.getHall()+"\',\'"+event.getPrice().toString()+"\',\'"+event.getEventID()+"\');";
            stmt.executeUpdate(sql);
        }
        c.commit();
        MessagePrinter.recordsCreated();
    }

    public ArrayList<TicketResource> getTickets() throws Exception {
        ArrayList <TicketResource> tickets=new ArrayList<>();
        rs = stmt.executeQuery( "SELECT * FROM tickets;" );

        while ( rs.next() ) {
            String  ticketID = rs.getString("ticketID");
            String  basicPrice = rs.getString("basicPrice");
            String  eventID = rs.getString("eventID");
            String  seat = rs.getString("seat");
            String  validationCode = rs.getString("validationCode");
            boolean isBooked=false;
            if (rs.getInt("isBooked")==1){
                isBooked=true;
            }
            tickets.add(new TicketResource(ticketID,eventID, basicPrice, seat,isBooked, validationCode));
        }

        return tickets;
    }

    public void addTicketsToDatabase(ArrayList<TicketResource> tickets) throws SQLException {
        for (TicketResource ticket:tickets){
            int status=0;
            if (ticket.isBooked()){
                status=1;
            }
            String sql = "INSERT INTO tickets (ticketID, basicPrice, eventID, seat, isBooked, validationCode) " +
                    "VALUES (\'"+ticket.getId()+"\',\'"+ticket.getPrice()+"\',\'"+ticket.getEventID()+"\',\'"+ticket.getSeat()+"\',\'"+ status+"\',\'"+ ticket.getValidationCode() +"\');";
            stmt.executeUpdate(sql);
        }
        c.commit();
        MessagePrinter.recordsCreated();
    }

    public void buyTicket(TicketResource ticket) throws SQLException {
        String sql = "UPDATE tickets SET isBooked=" +1+" WHERE ticketID=\'"+ticket.getId()+"\';";
        stmt.executeUpdate(sql);
        c.commit();
        MessagePrinter.recordsCreated();
    }


}
