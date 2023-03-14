package db;

import businessObjects.Event;
import businessObjects.Performance;
import Service.TheaterService;
import businessObjects.Price;
import resources.EventResource;
import rest.TheaterBoxOfficeApp;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

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

    public static ArrayList <Event> getTheaterProgram() throws Exception {
        ArrayList <Event> events=new ArrayList<>();
            rs = stmt.executeQuery( "SELECT * FROM program;" );
            while ( rs.next() ) {
                String  performanceName = rs.getString("performance");
                Date data = rs.getDate("data");
                Date  time = rs.getTime("time");
                String  hall = rs.getString("hall");
                BigDecimal price = rs.getBigDecimal("basicPrice");
                try {
                    ResultSet rs2 = stmt.executeQuery( "SELECT * FROM performances WHERE name =\'"+performanceName+"\';" );
                    Performance performance=null;
                    while ( rs2.next() ) {
                        performance=new Performance(rs2.getString("name"),rs2.getString("description") );
                    }
                    rs2.close();
                    events.add(new Event(performance, data, time, hall, new Price(price)));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

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
}
