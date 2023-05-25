package rest;

import Service.TheaterService;
import persistence.*;
import io.javalin.Javalin;
import persistence.jdbc.EventRepositoryJDBC;
import persistence.jdbc.PerformanceRepositoryJDBC;
import persistence.jdbc.TicketRepositoryJDBC;
import repositories.*;
import rest.mvc.Controller;
import rest.mvc.Model;
import services.BookOneWayTicketDomainServiceJDBC;
import services.BookYearTicketDomainServiceJDBC;


public class TheaterBoxOfficeApp {
    public static String JDBC_SQLITE_DATABASE = "jdbc:sqlite:0-cleanproject-plugins/0-cleanproject-plugins/src/main/resources/sqlite/db/theater-box-office.db";

    public static void main(String[] args) {
        Controller controller = new Controller(buildModel());
        Javalin app = Javalin.create().start(7771);
        app.get("/events/{eventID}", context -> {
            controller.getEvent(context);
        });
        app.get("/events", context -> {
            controller.getEventList(context);
        });
        app.get("/performances", context -> {
            controller.getPerformanceList(context);
        });
        app.get("/performances/{performanceName}", context -> {
            controller.getPerformance(context);
        });
        app.post("/addPerformances", context -> {
            controller.addPerformances(context);
        });
        app.post("/addEvents", context -> {
            controller.addEvents(context);
        });
        app.post("/buyOneWayTicket/{ticketID}", context -> {
            controller.buyTicket(context);
        });
        app.post("/buyYearTicket", context -> {
            controller.buyYearTicket(context);
        });
        app.post("/login", context -> {
            controller.login(context);
        });
        app.get("/logout", context -> {
            controller.logout(context);
        });

    }

    private static Model buildModel() {
        HallRepository hallRepositoryImplementation = new HallRepositoryImplementation();
        SeatRepository seatRepositoryImplementation = new SeatRepositoryImplementation(hallRepositoryImplementation);
        PerformanceRepository performanceRepositoryJDBC = new PerformanceRepositoryJDBC();
        TicketRepository ticketRepositoryJDBC = new TicketRepositoryJDBC(seatRepositoryImplementation);
        EventRepository eventRepositoryJDBC = new EventRepositoryJDBC(hallRepositoryImplementation, performanceRepositoryJDBC);
        return new Model(new TheaterService(hallRepositoryImplementation, seatRepositoryImplementation, performanceRepositoryJDBC, eventRepositoryJDBC, ticketRepositoryJDBC, new BookOneWayTicketDomainServiceJDBC(), new BookYearTicketDomainServiceJDBC()));
    }

}
