package converters;

import Service.TheaterService;
import businessObjects.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import resources.EventResource;
import resources.PerformanceResource;
import resources.TicketResource;

import java.util.ArrayList;
import java.util.UUID;

public class GsonConverter {

    public static String event2jsonString(EventResource event){
        if (event!=null){
            String result= "{'id':'"+ event.getEventID()+"','performance':'"+event.getPerformance()+"','data':'"+event.getDate().toString()+"','time':'"+event.getTime().toString()+"','hall':'"+event.getHall()+",\n{'tickets':[\n";
            for (TicketResource ticket:event.getTicketResources()){
                String status;
                if (ticket.isBooked()){
                    status="booked";
                }else{
                    status="available";
                }
                System.out.println(ticket.isBooked());
                result=result+"{'id':'"+ticket.getId()+"','seat number':'"+ticket.getSeat()+"','seat number':'"+ticket.getSeat()+"','status':'"+status+"','preis':'"+ticket.getPrice().toString()+"'},\n";
            }
            return result.substring(0,result.length()-2)+"]}";
        }else {
            return "{'message':'Database is empty'}";
        }

    }

    public static String eventList2jsonString(ArrayList <Event> events){
        if (!events.isEmpty()){
            String result="{'events':[";
            for (Event event:events){
                if (event!=null){
                int aTickets=0;
                int bTickets=0;
                if (!event.getTickets().isEmpty()){
                for (Ticket ticket:event.getTickets()){

                    if (ticket.isBooked()){
                        bTickets++;
                    }else{
                        aTickets++;
                    }
                }}
                result=result+"{'id':'"+ event.getId()+"','performance':'"+event.getPerformance().getName()+"','date':'"+event.getDate().toString()+"','time':'"+event.getTime().toString()+"','available tickets':'"+aTickets+"','booked tickets':'"+bTickets+"'},";
            }}
            return result.substring(0,result.length()-1)+"]}";
        }else{
           return  "{'message':'Database is empty'}";
        }


    }

    public static  String performance2jsonString(Performance performance){
        if (performance!=null){
            return "{'name':'"+ performance.getName()+"','description':'"+performance.getDescription()+"'}";
        }else{
            return "{'message':'Database is empty'}";
        }

    }

    public static String performanceList2jsonString(ArrayList <Performance> performances){
        if (!performances.isEmpty()){
        String result="{'performances':[";
        for (Performance performance:performances){
            if (performance!=null){
            result=result+"{'name':'"+ performance.getName()+"','description':'"+performance.getDescription()+"'},";
        }}
        return result.substring(0,result.length()-1)+"]}";
        }else{
            return "{'message':'Database is empty'}";}
    }

    public static String boughtTicket2jsonString(Ticket ticket) {
        if (ticket!=null){
            String result= "{'id':'"+ ticket.getId()+"','event':'"+ticket.getEventID()+"','seat':'"+ticket.getSeat().getSeatNumber()+"','price':'"+ticket.getPrice().toString()+"','is booked':'"+ticket.isBooked()+"'}";
            return result;
        }else{
            return  "{'message':'Your purchase could not be completed'}";
        }
    }

    public static ArrayList <EventResource> json2EventResourceList(String contextBody) throws JSONException {
        JSONObject performanceJson = new JSONObject(contextBody);
        JSONArray jsonArrayEvents = performanceJson.getJSONArray("program");
        ArrayList <EventResource> eventResources= new ArrayList<>();
        for (int i=0; i<jsonArrayEvents.length();i++){
            JSONObject pJ = jsonArrayEvents.getJSONObject(i);
            try {
                EventResource event = new EventResource(pJ.getString("performance"), pJ.getString("date"),pJ.getString("time"), pJ.getString("hall"),pJ.getString("basic price") );
                eventResources.add(event);
                return eventResources;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }
        return  null;

    }

    public static ArrayList <PerformanceResource> json2PerformanceList(String contextBody) throws JSONException {
        JSONObject performanceJson = new JSONObject(contextBody);
        JSONArray jsonArrayPerformances = performanceJson.getJSONArray("performances");
        ArrayList <PerformanceResource> performanceList= new ArrayList<>();
        for (int i=0; i<jsonArrayPerformances.length();i++){
            JSONObject pJ = jsonArrayPerformances.getJSONObject(i);
            PerformanceResource performanceResource = new PerformanceResource(pJ.getString("name"), pJ.getString("description"));
            performanceList.add(performanceResource);
        }
        return performanceList;
    }
    public static String status2jsonString(Boolean isSuccessful){
        if (isSuccessful){
            return "{'message':'Successful'}";
        }else{
            return "{'message':'Sorry!Your purchase could not be completed. Unknown problem ####'}";
        }

    }
}
