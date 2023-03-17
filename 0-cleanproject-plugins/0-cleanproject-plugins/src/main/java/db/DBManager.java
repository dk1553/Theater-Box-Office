package db;

import businessObjects.*;
import rest.TheaterBoxOfficeApp;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class DBManager {
    static Connection c = null;
    static Statement stmt = null;
    static   ResultSet rs=null;

    public DBManager() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection(TheaterBoxOfficeApp.BOX_OFFICE_DATABASE);
        c.setAutoCommit(false);
        stmt = c.createStatement();
        System.out.println("Opened database successfully");
    }
    public void close() throws SQLException {
        rs.close();
        stmt.close();
        c.close();
    }
    public static ArrayList <Performance> getRepertoire() throws SQLException {
        ArrayList <Performance> performances=new ArrayList<>();
            rs = stmt.executeQuery( "SELECT * FROM performances;" );
            while ( rs.next() ) {
                String  name = rs.getString("name");
                String  description = rs.getString("description");
                performances.add(new Performance(name, description));
            }
        return performances;
    }

    public static ArrayList <Event> getTheaterProgram(TheaterBuilding theaterBuilding) throws Exception {
        ArrayList <Event> events=new ArrayList<>();
            rs = stmt.executeQuery( "SELECT * FROM program;" );
        Statement stmt2 = c.createStatement();
            while ( rs.next() ) {
                String  eventID = rs.getString("eventID");
                String  performanceName = rs.getString("performance");
                SimpleDateFormat simpleDateFormatDate= new SimpleDateFormat("dd.MM.yyyy");
                SimpleDateFormat simpleDateTime= new SimpleDateFormat("hhh:mm");
                Date data =simpleDateFormatDate.parse(rs.getString("date"));
                Date  time =simpleDateTime.parse(rs.getString("time"));
                String  hall = rs.getString("hall");
                BigDecimal price = rs.getBigDecimal("basicPrice");
                try {

                    ResultSet rs2 = stmt2.executeQuery( "SELECT * FROM performances WHERE name =\'"+performanceName+"\';" );
                    Performance performance=null;
                    while ( rs2.next() ) {
                        performance=new Performance(rs2.getString("name"),rs2.getString("description") );
                    }
                    rs2.close();
                    ArrayList <Ticket> tickets= new ArrayList<>();
                    tickets.addAll(getTicketsOfEvent(theaterBuilding, eventID, hall));
                    if (!tickets.isEmpty()){
                        events.add(new Event(eventID,performance, data, time, theaterBuilding.findHallByName(hall), new Price(price),tickets ));

                    }else{
                        events.add(new Event(eventID,performance, data, time, theaterBuilding.findHallByName(hall), new Price(price), "noTickets"));

                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                stmt2.close();


            }

        return events;
    }

    public static  void addPerformancesToDatabase(ArrayList<Performance> performanceList) throws SQLException {
            for (Performance performance:performanceList){
                String sql = "INSERT INTO performances (name, description) " +
                        "VALUES (\'"+performance.getName()+"\',\'"+performance.getDescription()+"\');";
                stmt.executeUpdate(sql);
            }
            c.commit();
            System.out.println("Records created successfully");
    }

    public static  void addPerformanceToDatabase(Performance performance) throws SQLException {

            String sql = "INSERT INTO performances (name, description) " +
                    "VALUES (\'"+performance.getName()+"\',\'"+performance.getDescription()+"\');";
            stmt.executeUpdate(sql);

        c.commit();
        System.out.println("Records created successfully");
    }

    public void addEventsToDatabase(ArrayList<Event> events) throws SQLException {
        for (Event event:events){
            String sql = "INSERT INTO program (performance, date, time, hall, basicPrice, eventID) " +
                    "VALUES (\'"+event.getPerformance().getName()+"\',\'"+event.getDate().toString()+"\',\'"+event.getTime().toString()+"\',\'"+event.getHallName()+"\',\'"+event.getBasicPrice().toString()+"\',\'"+event.getId()+"\');";
            stmt.executeUpdate(sql);
        }
        c.commit();
        System.out.println("Records created successfully");
    }

    public ArrayList<Ticket> getTickets(TheaterBuilding theaterBuilding) throws Exception {
        ArrayList <Ticket> tickets=new ArrayList<>();
        rs = stmt.executeQuery( "SELECT * FROM tickets;" );
        Statement stmt2 = c.createStatement();
        while ( rs.next() ) {
            String  ticketID = rs.getString("ticketID");
            String  basicPrice = rs.getString("basicPrice");
            String  eventID = rs.getString("eventID");
            String  seat = rs.getString("seat");
            boolean isBooked=false;
            if (rs.getInt("isBooked")==1){
                isBooked=true;
            }
            String hallName="";
            try {
                ResultSet rs2 = stmt2.executeQuery( "SELECT * FROM program WHERE eventID =\'"+eventID+"\';" );
                while ( rs2.next() ) {
                    hallName=rs2.getString("hall");
                }
                rs2.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            stmt2.close();
            Hall hall= theaterBuilding.findHallByName(hallName);
            assert hall != null;
            tickets.add(new Ticket(ticketID,eventID, new Price(basicPrice), hall.findSeatById(seat),isBooked));
        }
        return tickets;
    }

    private static ArrayList<Ticket> getTicketsOfEvent(TheaterBuilding theaterBuilding, String eventID, String hallName) throws Exception {
        ArrayList <Ticket> tickets=new ArrayList<>();
        Statement stmt3 = c.createStatement();
        ResultSet resultSet = stmt3.executeQuery( "SELECT * FROM tickets WHERE eventID=\'"+eventID+"\';");

        while ( resultSet.next() ) {
            String  ticketID = resultSet.getString("ticketID");
            String  basicPrice = resultSet.getString("basicPrice");
            String  seat = resultSet.getString("seat");
            boolean isBooked=false;
            if (resultSet.getInt("isBooked")==1){
                isBooked=true;
            }



            Hall hall= theaterBuilding.findHallByName(hallName);
            assert hall != null;
            tickets.add(new Ticket(ticketID,eventID, new Price(basicPrice), hall.findSeatById(seat),isBooked));
            System.out.println("this++"+ticketID);
        }
        stmt3.close();
        resultSet.close();
        return tickets;
    }

    public void addTicketsToDatabase(ArrayList<Ticket> tickets, Price basicPrice, String eventID) throws SQLException {
        for (Ticket ticket:tickets){
            int status=0;
            if (ticket.isBooked()){
                status=1;
            }
            String sql = "INSERT INTO tickets (ticketID, basicPrice, eventID, seat, isBooked) " +
                    "VALUES (\'"+ticket.getId()+"\',\'"+basicPrice+"\',\'"+eventID+"\',\'"+ticket.getSeat().getSeatID()+"\',\'"+ status +"\');";
            stmt.executeUpdate(sql);
        }
        c.commit();
        System.out.println("Records created successfully");
    }

    public void buyTicket(Ticket ticket) throws SQLException {
        String sql = "UPDATE tickets SET isBooked=" +1+" WHERE ticketID=\'"+ticket.getId()+"\';";
        stmt.executeUpdate(sql);
        c.commit();
        System.out.println("Records created successfully");
    }
}
