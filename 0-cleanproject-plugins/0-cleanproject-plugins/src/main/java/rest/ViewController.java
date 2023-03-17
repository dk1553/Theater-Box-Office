package rest;

import businessObjects.*;
import Service.TheaterService;

import converters.GsonFormConverter;
import io.javalin.http.Context;
import org.json.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

public class ViewController {
   private static TheaterService service;

    public static void start(TheaterService service) {
        ViewController.service = service;
        try {
           /* for (Event event:service.getEventRepository().findAllEvents()){
                String hallName=event.getHallName();
                event.setHall(service.getTheaterBuilding().findHallByName(hallName));

            }*/


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void getEventList(Context context) {
        context.json(
                GsonFormConverter.eventList2jsonformatString(
                        service.showProgramUseCase()));
    }

    public static void getEvent(Context context) {
        context.json(
                GsonFormConverter.event2jsonformatString(
                        service.showEventUseCase(
                                context.pathParam("eventID"))));

    }

    public static void getPerformanceList(Context context) {
          context.json(
                  GsonFormConverter.performanceList2jsonformatString(
                          service.showRepertoireUseCase()));

    }
    public static void getPerformance(Context context) {
        context.json(GsonFormConverter.performance2jsonformatString(service.showPerformanceUseCase(context.pathParam("performanceName"))));
    }

    public  static void addPerformances(Context context) throws JSONException, SQLException, ClassNotFoundException {
        context.status(200);
        context.json("{'message':'Successful'}");
        JSONObject performanceJson = new JSONObject(context.body());
        JSONArray jsonArrayPerformances = performanceJson.getJSONArray("performances");
        ArrayList <Performance> performanceList= new ArrayList<>();
        for (int i=0; i<jsonArrayPerformances.length();i++){
            JSONObject pJ = jsonArrayPerformances.getJSONObject(i);
            Performance performance = new Performance(pJ.getString("name"), pJ.getString("description"));
            performanceList.add(performance);
            System.out.println(performance.getName());
        }

        service.updateRepertoireUseCase(performanceList);

    }

    public static void addEvents(Context context) throws JSONException {
        context.status(200);
        context.json("{'message':'Successful'}");
        JSONObject performanceJson = new JSONObject(context.body());
        JSONArray jsonArrayEvents = performanceJson.getJSONArray("program");
        ArrayList <Event> eventList= new ArrayList<>();
        for (int i=0; i<jsonArrayEvents.length();i++){
            JSONObject pJ = jsonArrayEvents.getJSONObject(i);
            try {
                Performance performance =service.getPerformancesRepository().findPerformanceByName(pJ.getString("performance"));
                Hall hall= service.getTheaterBuilding().findHallByName( pJ.getString("hall"));
                Price price= new Price(pJ.getString("basic price"));
                SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.yyyy");
                Date date = formatterDate.parse(pJ.getString("date"));
                SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm");
                Date time = formatterTime.parse(pJ.getString("time"));
                Event event = new Event(performance, date,time, hall,price );
                eventList.add(event);
                System.out.println(performance.getName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        service.updateTheaterProgramUseCase(eventList);
    }

    public static void buyTicket(Context context) throws JSONException {
        context.status(200);
        JSONObject userJson = new JSONObject(context.body());
        JSONObject pJ = userJson.getJSONObject("user");
        context.json(
                GsonFormConverter.boughtTicket2jsonformatString(
                        service.buyTicketUseCase(
                                context.pathParam("ticketID"),
                                pJ.getString("first name"),
                                pJ.getString("last name"))));


    }
}


