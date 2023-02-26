import io.javalin.Javalin;

public class Main{
    public static void main (String[] args){
        Javalin app=Javalin.create().start(7777);
        app.get("/events", EventController::getAllEvents);
        app.get("/events/{eventID}", EventController::getEvent);
    }
}
