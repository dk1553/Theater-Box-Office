import com.google.gson.JsonSyntaxException;
import io.javalin.http.Context;

import java.util.ArrayList;

public class ViewController {
   private static TheaterService service;
    private ViewController(){}
    public static void start(){
        service= new TheaterService();
        service.loadRepertoire(DataConrtoller.getRepertoire());
    }


    static String[] avalibleEvents ={"1", "2"};
    public  static void getAllEvents (Context context) throws JsonSyntaxException {
        // for test
        context.json("{'Event1':{'id':1,'name':'Edward II'},'Event2':{'id':2,'name':'Klavierstunde'}}");
    }

    public static void getEvent(Context context) {
        for (String event: avalibleEvents){
            if (event.contains(context.pathParam("eventID"))){
                context.result(event);
                return;
            }
        }
        context.result("No events found");
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
        context.result(result);
    }
}


