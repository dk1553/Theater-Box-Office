package converters;

import businessObjects.Event;
import businessObjects.Performance;
import businessObjects.Ticket;

import java.util.ArrayList;

public class GsonFormConverter {

    public static String event2jsonformatString(Event event){
        if (event!=null){
            String result= "{'id':'"+ event.getId()+"','performance':'"+event.getPerformance().getName()+"','data':'"+event.getDate().toString()+"','time':'"+event.getTime().toString()+"','hall':'"+event.getHall().getHallName()+",\n{'tickets':[\n";
            for (Ticket ticket:event.getTickets()){
                String status;
                if (ticket.isBooked()){
                    status="booked";
                }else{
                    status="available";
                }
                System.out.println(ticket.isBooked());
                result=result+"{'id':'"+ticket.getId()+"','seat number':'"+ticket.getSeat().getType().toString()+"','seat number':'"+ticket.getSeat().getSeatNumber()+"','status':'"+status+"','preis':'"+ticket.getPrice().toString()+"'},\n";
            }
            return result.substring(0,result.length()-2)+"]}";
        }else {
            return "{'message':'Database is empty'}";
        }

    }

    public static String eventList2jsonformatString(ArrayList <Event> events){
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

    public static  String performance2jsonformatString(Performance performance){
        if (performance!=null){
            return "{'name':'"+ performance.getName()+"','description':'"+performance.getDescription()+"'}";
        }else{
            return "{'message':'Database is empty'}";
        }

    }

    public static String performanceList2jsonformatString(ArrayList <Performance> performances){
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

    public static String boughtTicket2jsonformatString(Ticket ticket) {
        if (ticket!=null){
            String result= "{'id':'"+ ticket.getId()+"','event':'"+ticket.getEventID()+"','seat':'"+ticket.getSeat().getSeatNumber()+"','price':'"+ticket.getPrice().toString()+"','is booked':'"+ticket.isBooked()+"'}";
            return result;
        }else{
            return  "{'message':'Your purchase could not be completed'}";
        }


    }
}
