package rest;

import businessObjects.*;
import Service.TheaterService;

import converters.GsonConverter;
import io.javalin.http.Context;
import org.json.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;
import resources.EventMapper;
import resources.PerformanceMapper;

public class ViewController {
   private static TheaterService service;
   private  static EventMapper eventMapper;
    private  static PerformanceMapper performanceMapper;


    public static void start(TheaterService service) {
        ViewController.service = service;
        eventMapper=new EventMapper();
        performanceMapper=new PerformanceMapper();
    }


    public static void getEventList(Context context) {
        context.json(
                GsonConverter.eventList2jsonString(
                        service.showProgramUseCase()));
    }

    public static void getEvent(Context context) {
        context.json(
                GsonConverter.event2jsonString(
                        service.showEventUseCase(
                                context.pathParam("eventID"))));

    }

    public static void getPerformanceList(Context context) {
          context.json(
                  GsonConverter.performanceList2jsonString(
                          service.showRepertoireUseCase()));

    }
    public static void getPerformance(Context context) {
        context.json(
                GsonConverter.performance2jsonString(
                        service.showPerformanceUseCase(
                                context.pathParam("performanceName"))));
    }

    public  static void addPerformances(Context context) throws Exception {
        context.status(200);
        Boolean status= service.updateRepertoireUseCase(
                performanceMapper.map(
                        GsonConverter.json2PerformanceList(
                                context.body())));
        context.json(GsonConverter.status2jsonString(status));

    }

    public static void addEvents(Context context) throws Exception {
        context.status(200);
        Boolean status= service.updateTheaterProgramUseCase(
                eventMapper.map(
                        Objects.requireNonNull(
                                GsonConverter.json2EventResourceList(
                                        context.body())),service.getPerformanceRepository(), service.getTheaterBuilding(), service.getTicketRepository()));
        context.json(GsonConverter.status2jsonString(status));
    }

    public static void buyTicket(Context context) throws JSONException {
        context.status(200);
        JSONObject userJson = new JSONObject(context.body());
        JSONObject pJ = userJson.getJSONObject("user");
        context.json(
                GsonConverter.boughtTicket2jsonString(
                        service.buyTicketUseCase(
                                context.pathParam("ticketID"),
                                pJ.getString("first name"),
                                pJ.getString("last name"))));


    }
}


