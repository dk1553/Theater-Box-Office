import java.sql.*;
import java.util.ArrayList;

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
}
