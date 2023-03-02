import io.javalin.Javalin;

public class JavalinMain {
   public static String BOX_OFFICE_DATABASE="jdbc:sqlite:D:/Theater-Box-Office/0-cleanproject-plugins/0-cleanproject-plugins/src/main/resources/sqlite/db/theater-box-office.db";
    public static void main (String[] args){
        Javalin app=Javalin.create().start(7777);
        ViewController.start();
        //test functions
        app.get("/events/{eventID}", ViewController::getEvent);


        app.get("/events", ViewController::getEventList);
        app.get("/performances", ViewController::getPerformanceList);
        app.get("/performances/{performanceName}", ViewController::getPerformance);
    }


}
