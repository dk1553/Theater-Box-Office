package converters;

import businessObjects.Event;
import businessObjects.Performance;
import businessObjects.Ticket;

import java.util.ArrayList;

public class GsonFormConverter {

    public static String event2jsonformatString(Event event){
        String result= "{'id':'"+ event.getId()+"','performance':'"+event.getPerformance().getName()+"','data':'"+event.getDate().toString()+"','time':'"+event.getTime().toString()+"','hall':'"+event.getHall().getHallName()+",\n{'tickets':[\n";
        for (Ticket ticket:event.getTickets()){
            String status;
            if (ticket.isBooked()){
                status="booked";
            }else{
                status="available";
            }
            result=result+"{'id':'"+ticket.getId()+"','seat number':'"+ticket.getSeat().getType().toString()+"','seat number':'"+ticket.getSeat().getSeatNumber()+"','status':'"+status+"','preis':'"+ticket.getPrice().toString()+"'},\n";
        }
        return result.substring(0,result.length()-2)+"]}";
    }

    public static String eventList2jsonformatString(ArrayList <Event> events){
        String result="{'events':[";
        for (int i=0;i<events.size(); i++){
            result=result+"{'id':'"+ events.get(i).getId()+"','performance':'"+events.get(i).getPerformance().getName()+"','date':'"+events.get(i).getDate().toString()+"','time':'"+events.get(i).getTime().toString()+"'},";
        }
        return result.substring(0,result.length()-1)+"]}";

    }

    public static  String performance2jsonformatString(Performance performance){
        return "{'name':'"+ performance.getName()+"','description':'"+performance.getDescription()+"'}";
    }

    public static String performanceList2jsonformatString(ArrayList <Performance> performances){
        String result="{'performances':[";
        for (int i=0;i<performances.size(); i++){
            result=result+"{'name':'"+ performances.get(i).getName()+"','description':'"+performances.get(i).getDescription()+"'},";
        }
        return result.substring(0,result.length()-1)+"]}";
    }

    public static String boughtTicket2jsonformatString(Ticket ticket) {
        String result= "{'id':'"+ ticket.getId()+"','event':'"+ticket.getEventID()+"','seat':'"+ticket.getSeat().getSeatNumber()+"','price':'"+ticket.getPrice().toString()+"','is booked':'"+ticket.isBooked()+"'}";

        return result;
    }
}
