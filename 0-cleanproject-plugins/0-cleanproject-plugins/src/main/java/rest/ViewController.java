package rest;

import businessObjects.Event;
import businessObjects.Performance;
import Service.TheaterService;

import converters.GsonFormConverter;
import io.javalin.http.Context;
import org.json.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Repertoire;
import persistence.TheaterProgram;

public class ViewController {
   private static TheaterService service;
    public static void start(){
        service= new TheaterService(new Repertoire(), new TheaterProgram());
        try {
            for (Event event:service.getEventRepository().findAllEvents()){
                String hallName=event.getHallName();
                event.setHall(service.findHallByName(hallName));

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
            if (String.valueOf(event.getId()).equalsIgnoreCase(context.pathParam("eventID"))){
                result=GsonFormConverter.event2jsonformString(event);
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
}


