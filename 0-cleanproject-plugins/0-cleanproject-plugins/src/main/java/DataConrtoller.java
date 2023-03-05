import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DataConrtoller {
    public static ArrayList <Performance> getRepertoire() {

        Connection c = null;
        Statement stmt = null;
        ArrayList <Performance> performances=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(JavalinMain.BOX_OFFICE_DATABASE);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM performances;" );

            while ( rs.next() ) {
                String  name = rs.getString("name");
                String  description = rs.getString("description");
                performances.add(new Performance(name, description));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return performances;
    }

    public static ArrayList <Event> getTheaterProgram(TheaterService service) {

        Connection c = null;
        Statement stmt = null;
        ArrayList <Event> events=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(JavalinMain.BOX_OFFICE_DATABASE);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM program;" );


            while ( rs.next() ) {
                String  performance = rs.getString("performance");
                Date data = rs.getDate("data");
                Date  time = rs.getTime("time");
                String  hall = rs.getString("hall");
                BigDecimal price = rs.getBigDecimal("basicPrice");
                events.add(new Event(service.findPerformance(performance), data, time, service.findHall(hall), new Price(price)));

            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println(events.size());
        return events;
    }

    public static  void addPerformancesToDatabase(ArrayList<Performance> performanceList){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(JavalinMain.BOX_OFFICE_DATABASE);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();

            for (Performance performance:performanceList){
                String sql = "INSERT INTO performances (name, description) " +
                        "VALUES (\'"+performance.getName()+"\',\'"+performance.getDescription()+"\');";
                stmt.executeUpdate(sql);
            }
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}
