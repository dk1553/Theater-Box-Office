package rest;

import Service.TheaterService;
import businessObjects.TheaterBuilding;
import io.javalin.Javalin;
import persistence.PerformanceRepositoryJDBC;
import persistence.EventRepositoryJDBC;
import persistence.TicketRepositoryJDBC;

public class TheaterBoxOfficeApp {
   public static String JDBC_SQLITE_DATABASE ="jdbc:sqlite:D:/Theater-Box-Office/0-cleanproject-plugins/0-cleanproject-plugins/src/main/resources/sqlite/db/theater-box-office.db";
    public static void main (String[] args){
        Javalin app=Javalin.create().start(7771);

        TheaterBuilding theaterBuilding=new TheaterBuilding();
        PerformanceRepositoryJDBC performanceRepositoryJDBC= new PerformanceRepositoryJDBC();
        TicketRepositoryJDBC ticketRepositoryJDBC= new TicketRepositoryJDBC(theaterBuilding);

        EventRepositoryJDBC eventRepositoryJDBC=new EventRepositoryJDBC(theaterBuilding, ticketRepositoryJDBC, performanceRepositoryJDBC);


        TheaterService theaterService= new TheaterService(theaterBuilding, performanceRepositoryJDBC,eventRepositoryJDBC, ticketRepositoryJDBC);
        ViewController.start(theaterService);


        app.get("/events/{eventID}", ViewController::getEvent);
        app.get("/events", ViewController::getEventList);
        app.get("/performances", ViewController::getPerformanceList);
        app.get("/performances/{performanceName}", ViewController::getPerformance);
        app.post("/addPerformances", ViewController::addPerformances);
        app.post("/addEvents", ViewController::addEvents);
        app.post("/buyTicket/{ticketID}", ViewController::buyTicket);
    }




}
