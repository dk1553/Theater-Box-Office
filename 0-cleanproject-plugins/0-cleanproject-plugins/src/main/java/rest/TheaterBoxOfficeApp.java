package rest;

import Service.TheaterService;
import mapper.*;
import persistence.*;
import io.javalin.Javalin;
import persistence.jdbc.EventRepositoryJDBC;
import persistence.jdbc.PerformanceRepositoryJDBC;
import persistence.jdbc.TicketRepositoryJDBC;
import repositories.*;
import rest.converters.ConvertFromJsonService;
import rest.converters.ConvertToJsonService;
import services.BookOneWayTicketDomainServiceJDBC;
import services.BookYearTicketDomainServiceJDBC;

import java.util.Objects;


public class TheaterBoxOfficeApp {
    public static String JDBC_SQLITE_DATABASE = "jdbc:sqlite:0-cleanproject-plugins/0-cleanproject-plugins/src/main/resources/sqlite/db/theater-box-office.db";

    private static TheaterService service;

    private static EventMapper eventMapper;
    private static EventResourceMapper eventResourceMapper;
    private static PerformanceMapper performanceMapper;
    private static PerformanceResourceMapper performanceResourceMapper;
    private static TicketResourceMapper ticketResourceMapper;


    public static void main(String[] args) {
        HallRepository hallRepositoryImplementation = new HallRepositoryImplementation();
        SeatRepository seatRepositoryImplementation = new SeatRepositoryImplementation(hallRepositoryImplementation);
        PerformanceRepository performanceRepositoryJDBC = new PerformanceRepositoryJDBC();
        TicketRepository ticketRepositoryJDBC = new TicketRepositoryJDBC(seatRepositoryImplementation);
        EventRepository eventRepositoryJDBC = new EventRepositoryJDBC(hallRepositoryImplementation, performanceRepositoryJDBC);
        service = new TheaterService(hallRepositoryImplementation, seatRepositoryImplementation, performanceRepositoryJDBC, eventRepositoryJDBC, ticketRepositoryJDBC, new BookOneWayTicketDomainServiceJDBC(), new BookYearTicketDomainServiceJDBC());

        eventMapper = new EventMapper();
        performanceMapper = new PerformanceMapper();
        performanceResourceMapper = new PerformanceResourceMapper();
        eventResourceMapper = new EventResourceMapper();
        ticketResourceMapper = new TicketResourceMapper();

        Javalin app = Javalin.create().start(7771);


        app.get("/events/{eventID}", context -> {
            String viewData = ConvertToJsonService.eventResourceList2jsonString(eventResourceMapper.map(
                    service.showProgramUseCase(), service.getTicketRepository()));
            context.json(viewData);
        });
        app.get("/events", context -> {
            String eventID=context.pathParam("eventID");
            String viewData = ConvertToJsonService.eventResource2jsonString(eventResourceMapper.map(
                    service.showEventUseCase(eventID), service.getTicketRepository()));
            context.json(viewData);
        });
        app.get("/performances", context -> {
            String viewData = ConvertToJsonService.performanceResourceList2jsonString(performanceResourceMapper.map(
                    service.showRepertoireUseCase()));
            context.json(viewData);
        });
        app.get("/performances/{performanceName}", context -> {
            String performanceName=context.pathParam("performanceName");
            String viewData =
                    ConvertToJsonService.performanceResource2jsonString(performanceResourceMapper.map(
                            service.showPerformanceUseCase(performanceName)));
            context.json(viewData);
        });
        app.post("/addPerformances", context -> {
            String viewData = ConvertToJsonService.loginMessage2jsonString();
            int viewStatus = 401;
            if ((context.cookie("role") != null) && (Objects.equals(context.cookie("role"), "admin"))) {
                String json=context.body();
                Boolean status = service.updateRepertoireUseCase(
                        performanceMapper.map(
                                ConvertFromJsonService.json2PerformanceList(json)));

                viewData = ConvertToJsonService.status2jsonString(status);
                viewStatus = 200;
            }
            context.status(viewStatus);
            context.json(viewData);
        });
        app.post("/addEvents", context -> {
            String viewData = ConvertToJsonService.loginMessage2jsonString();
            int viewStatus = 401;
            if ((context.cookie("role") != null) && (Objects.equals(context.cookie("role"), "admin"))) {
                String json=context.body();
                Boolean status = service.updateTheaterProgramUseCase(
                        eventMapper.mapNewObject(ConvertFromJsonService.json2EventResourceList(
                                json), service.getPerformanceRepository(), service.getHallRepository()));
                viewData = ConvertToJsonService.status2jsonString(status);
                viewStatus = 200;
            }
            context.status(viewStatus);
            context.json(viewData);
        });
        app.post("/buyOneWayTicket/{ticketID}", context -> {
            String json=context.body();
            String ticketID=context.pathParam("ticketID");
            String[] username = ConvertFromJsonService.json2Username(json);
            String viewData =
                    ConvertToJsonService.boughtTicket2jsonString(ticketResourceMapper.map(
                            service.buyTicketUseCase(
                                    ticketID, username[0], username[1])));
            context.status(200);
            context.json(viewData);
        });
        app.post("/buyYearTicket", context -> {
            String json=context.body();
            String[] username = ConvertFromJsonService.json2Username(json);
            String viewData =
                    ConvertToJsonService.boughtTicket2jsonString(ticketResourceMapper.map(
                            service.buyYearTicketUseCase(
                                    username[0], username[1])));
            context.status(200);
            context.json(viewData);
        });
        app.post("/login", context -> {
            String json=context.body();
            String[] cred = ConvertFromJsonService.json2Credentials(json);
            Boolean adminIsVerified = service.verifyAdmin(cred[0], cred[1]);
            if (adminIsVerified) {
                context.cookie("role", "admin");
            } else {
                context.cookie("role", "null");
                String viewData = ConvertToJsonService.status2jsonString(true);
                context.status(200);
                context.json(viewData);
            }
            String viewData = ConvertToJsonService.status2jsonString(adminIsVerified);
            context.status(200);
            context.json(viewData);
        });
        app.get("/logout", context -> {
            context.cookie("role", "null");
            String viewData = ConvertToJsonService.status2jsonString(true);
            context.status(200);
            context.json(viewData);
        });

    }



}
