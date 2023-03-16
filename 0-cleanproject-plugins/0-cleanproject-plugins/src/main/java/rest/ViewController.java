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
            for (Event event:service.getEventRepository().findAllEvents()){
                String hallName=event.getHallName();
                event.setHall(service.getTheaterBuilding().findHallByName(hallName));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void getEventList(Context context) {
        ArrayList <Event> events= service.showProgramUseCase();
        if (events.size()>0){
          context.json(GsonFormConverter.eventList2jsonformatString(events));
        }else{
            context.json("{'message':'Database is empty'}");
        }
    }

    public static void getEvent(Context context) {
        String result="";
        for (Event event: service.showProgramUseCase()){
            if (event.getId().equalsIgnoreCase(context.pathParam("eventID"))){
                result=GsonFormConverter.event2jsonformatString(event);
                context.json(result);
                return;
            }
        }
        if (result.length()==0){
            context.json("{'message':'Database is empty'}");
        }
    }

    public static void getPerformanceList(Context context) {
        ArrayList <Performance> performances= service.showRepertoireUseCase();
        if (performances.size()>0){
          context.json(GsonFormConverter.performanceList2jsonformatString(performances));
        }else{
            context.json("{'message':'Database is empty'}");
        }
    }
    public static void getPerformance(Context context) {
        String result="";
        for (Performance performance:service.showRepertoireUseCase()){
            if (performance.getName().equalsIgnoreCase(context.pathParam("performanceName"))){
               context.json(GsonFormConverter.performance2jsonformatString(performance));
                return;
            }
        }
        if (result.length()==0){
            context.json("{'message':'Database is empty'}");
        }

    }

    public static void addEvent(Context context) {
        context.status(200);
        context.json("{'message':'Successful'}");

        service.getPerformancesRepository().addPerformance(new Performance(context.pathParam("name"),context.pathParam("description")
        ));
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

    public static void buyTicket(Context context) {
        context.status(200);

        Ticket boughtTicket=service.buyTicketUseCase(context.pathParam("ticketID"));
        if (boughtTicket!=null){
            context.json(GsonFormConverter.boughtTicket2jsonformatString(boughtTicket));
        }else{
            context.json("{'message':'Your purchase could not be completed'}");
        }

    }
}


