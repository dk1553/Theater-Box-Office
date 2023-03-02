import com.google.gson.JsonSyntaxException;
import io.javalin.http.Context;

import java.util.ArrayList;

public class ViewController {
   private static TheaterService service;
    private ViewController(){}
    public static void start(){
        service= new TheaterService();
        service.loadRepertoire(DataConrtoller.getRepertoire());
        service.loadTheaterProgram(DataConrtoller.getTheaterProgram(service));
        System.out.println(service.getTheaterProgram().getEventList().size());
    }


    static String[] avalibleEvents ={"1", "2"};
    public static void getEventList(Context context) {
        ArrayList <Event> events= ShowProgram.show(service);
        String result="{'events':[";
        if (events.size()>0){
            for (int i=0;i<events.size(); i++){
                result=result+"{'id':'"+ events.get(i).getId()+"','performance':'"+events.get(i).getPerformance().getName()+"','data':'"+events.get(i).getDate().toString()+"','time':'"+events.get(i).getTime().toString()+"'},";
            }
            result=result.substring(0,result.length()-1)+"]}";
        }else{
            result="{'message':'Database is empty'}";
        }
        context.result(result);
    }

    public static void getEvent(Context context) {
        for (String event: avalibleEvents){
            if (event.contains(context.pathParam("eventID"))){
                context.result(event);
                return;
            }
        }
        context.json("No events found");
    }

    public static void getPerformanceList(Context context) {
        ArrayList <Performance> performances= ShowRepertoire.show(service);
        String result="{'performances':[";
        if (performances.size()>0){
            for (int i=0;i<performances.size(); i++){
                result=result+"{'name':'"+ performances.get(i).getName()+"','description':'"+performances.get(i).getDescription()+"'},";
            }
            result=result.substring(0,result.length()-1)+"]}";
        }else{
            result="{'message':'Database is empty'}";
        }
        context.json(result);
    }
    public static void getPerformance(Context context) {

        String result="";
        for (Performance performance:ShowRepertoire.show(service)){

            if (performance.getName().contains(context.pathParam("performanceName"))){
                result=result+"{'name':'"+ performance.getName()+"','description':'"+performance.getDescription()+"'}";
                context.json(result);
                return;
            }
        }
        if (result.length()==0){
            context.json("{'message':'Database is empty'}");
        }

    }
}


