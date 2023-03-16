package rest;

import Service.TheaterService;
import io.javalin.Javalin;
import persistence.Repertoire;
import businessObjects.TheaterBuilding;
import persistence.TheaterProgram;

public class TheaterBoxOfficeApp {
   public static String BOX_OFFICE_DATABASE="jdbc:sqlite:D:/Theater-Box-Office/0-cleanproject-plugins/0-cleanproject-plugins/src/main/resources/sqlite/db/theater-box-office.db";
    public static void main (String[] args){
        Javalin app=Javalin.create().start(7771);

        TheaterService theaterService= new TheaterService(new Repertoire(), new TheaterProgram());
        ViewController.start(theaterService);


        app.get("/events/{eventID}", ViewController::getEvent);
        app.get("/events", ViewController::getEventList);
        app.get("/performances", ViewController::getPerformanceList);
        app.get("/performances/{performanceName}", ViewController::getPerformance);
        app.post("/addPerformances", ViewController::addPerformances);
        app.post("/addEvents", ViewController::addEvents);
    }




}
