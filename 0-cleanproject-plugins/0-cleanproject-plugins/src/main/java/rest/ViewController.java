package rest;

import mapper.*;
import Service.TheaterService;

import converters.GsonConverter;
import io.javalin.http.Context;

import org.json.JSONException;
import org.json.JSONObject;
import mapper.TicketMapper;

import java.util.Objects;
import java.util.zip.CheckedOutputStream;

public class ViewController {
   private static TheaterService service;
   private  static EventMapper eventMapper;
    private  static EventResourceMapper eventResourceMapper;
    private  static PerformanceMapper performanceMapper;
    private  static PerformanceResourceMapper performanceResourceMapper;

    private  static TicketMapper ticketMapper;
    private  static TicketResourceMapper ticketResourceMapper;



    public static void start(TheaterService service, Context context) {
        ViewController.service = service;
        eventMapper=new EventMapper();
        performanceMapper=new PerformanceMapper();
        performanceResourceMapper= new PerformanceResourceMapper();
        eventResourceMapper=new EventResourceMapper();
        ticketMapper= new TicketMapper();
        ticketResourceMapper= new TicketResourceMapper();
        context.cookie("role", "null");
    }


    public static void getEventList(Context context) throws Exception {
        context.json(
                GsonConverter.eventResourceList2jsonString(eventResourceMapper.map(
                        service.showProgramUseCase())));
    }

    public static void getEvent(Context context) {
        context.json(
                GsonConverter.eventResource2jsonString(eventResourceMapper.map(
                        service.showEventUseCase(
                                context.pathParam("eventID")))));

    }

    public static void getPerformanceList(Context context) throws Exception {
          context.json(
                  GsonConverter.performanceResourceList2jsonString(performanceResourceMapper.map(
                          service.showRepertoireUseCase())));

    }
    public static void getPerformance(Context context) throws Exception {
        context.json(
                GsonConverter.performanceResource2jsonString(performanceResourceMapper.map(
                        service.showPerformanceUseCase(
                                context.pathParam("performanceName")))));
    }

    public  static void addPerformances(Context context) throws Exception {
        if ((context.cookie("role")!=null)&&(Objects.equals(context.cookie("role"), "admin"))){
            context.status(200);
            Boolean status= service.updateRepertoireUseCase(
                    performanceMapper.map(
                            GsonConverter.json2PerformanceList(
                                    context.body())));

            context.json(GsonConverter.status2jsonString(status));
        }else{
            context.status(401);
            context.json("'message':'please log in to use this function'");
        }
    }

    public static void addEvents(Context context) throws Exception {
        if ((context.cookie("role")!=null)&&(Objects.equals(context.cookie("role"), "admin"))){
            context.status(200);
            Boolean status= service.updateTheaterProgramUseCase(
                    eventMapper.mapNewObject(GsonConverter.json2EventResourceList(
                            context.body()),service.getPerformanceRepository(), service.getTheaterBuilding(), service.getTicketRepository()));
            context.json(GsonConverter.status2jsonString(status));
        }else{
            context.status(401);
            context.json("'message':'please log in to use this function'");
        }
    }

    public static void buyTicket(Context context) throws Exception {
        context.status(200);
        JSONObject userJson = new JSONObject(context.body());
        JSONObject pJ = userJson.getJSONObject("user");
        context.json(
                GsonConverter.boughtTicket2jsonString(ticketResourceMapper.map(
                        service.buyTicketUseCase(
                                context.pathParam("ticketID"),
                                pJ.getString("first name"),
                                pJ.getString("last name")))));


    }

    public static void login(Context context) throws JSONException {
        context.status(200);
        JSONObject userJson = new JSONObject(context.body());
        JSONObject pJ = userJson.getJSONObject("admin");
        Boolean adminIsVerified=service.verifyAdmin(pJ.getString("username"), pJ.getString("password"));
        if (adminIsVerified){
            context.cookie("role","admin");
        }else {
           logout(context);
        }
        context.json(GsonConverter.status2jsonString(adminIsVerified));

    }

    public static void logout(Context context) {
        context.status(200);
        context.cookie("role", "null");
        context.json(GsonConverter.status2jsonString(true));
    }
}


