package rest.converters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import resources.EventResource;
import resources.PerformanceResource;
import resources.TicketResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConvertToJsonService {

    static final String messageSuccessful = "{'message':'Successful'}";
    static final String messageDBisEmpty = "{'message':'Something went wrong :/'}";
    static final String messageLogin = "'message':'Please log in to use this function!'";
    static final String messageNotCompleted = "{'message':'Your purchase could not be completed'}";
    static final String messageUnknownProblem = "{'message':'Sorry! Your purchase could not be completed. Unknown problem ####'}";


    public static String eventResource2jsonString(EventResource event) {
        if (event == null) {
            return messageDBisEmpty;
        }
        StringBuilder result = new StringBuilder("{'id':'" + event.getEventID() + "','performance':'" + event.getPerformance() + "','data':'" + event.getDate() + "','time':'" + event.getTime() + "','hall':'" + event.getHall() + ",\n{'tickets':[\n");
        for (TicketResource ticket : event.getTicketResources()) {
            String status;
            if (ticket.isBooked()) {
                status = "booked";
            } else {
                status = "available";
            }
            result.append("{'id':'").append(ticket.getId()).append("','seat number':'").append(ticket.getSeat()).append("','status':'").append(status).append("','price':'").append(ticket.getPrice()).append(" ").append(ticket.getCurrency()).append("'},\n");
        }
        return result.substring(0, result.length() - 2) + "]}";


    }

    public static String eventResourceList2jsonString(List<EventResource> events) {
        if ((Objects.isNull(events)) || (events.isEmpty())) {
            return messageDBisEmpty;
        }
        StringBuilder result = new StringBuilder("{'events':[");
        for (EventResource event : events) {
            if (event != null) {
                if ((event.getTicketResources() != null) && (!event.getTicketResources().isEmpty())) {
                    result.append("{'id':'").append(event.getEventID()).append("','performance':'").append(event.getPerformance()).append("','date':'").append(event.getDate()).append("','time':'").append(event.getTime()).append("','available tickets':'").append(event.getNumberOfAvailableSeats()).append("','booked tickets':'").append(event.getNumberOfBookedSeats()).append("'},");

                } else {
                    result.append("{'id':'").append(event.getEventID()).append("','performance':'").append(event.getPerformance()).append("','date':'").append(event.getDate()).append("','time':'").append(event.getTime()).append("','available tickets':'").append("no data").append("','booked tickets':'").append("no data").append("'},");

                }
            }
        }
        return result.substring(0, result.length() - 1) + "]}";


    }

    public static String performanceResource2jsonString(PerformanceResource performance) {
        if (performance == null) {
            return messageDBisEmpty;
        }
        return "{'name':'" + performance.getName() + "','description':'" + performance.getDescription() + "'}";


    }

    public static String loginMessage2jsonString() {
        return messageLogin;

    }

    public static String performanceResourceList2jsonString(List<PerformanceResource> performances) {
        if (performances.isEmpty()) {
            return messageDBisEmpty;
        }
        StringBuilder result = new StringBuilder("{'performances':[");
        for (PerformanceResource performance : performances) {
            if (performance != null) {
                result.append("{'name':'").append(performance.getName()).append("','description':'").append(performance.getDescription()).append("'},");
            }
        }
        return result.substring(0, result.length() - 1) + "]}";

    }

    public static String boughtTicket2jsonString(TicketResource ticket) {
        if (ticket == null) {
            return messageNotCompleted;
        }
        String result = "{'id':'";

        result = result + ticket.getId();
        if (!ticket.getEventID().equals("")) {
            result = result + "','event':'" + ticket.getEventID() + "','seat':'" + ticket.getSeat();
        }
        result = result + "','price':'" + ticket.getPrice() + "','is booked':'" + ticket.isBooked() + "','validation code':'" + ticket.getValidationCode();
        if (!ticket.getEndOfValidity().equals("")) {
            result = result + "','valid until':'" + ticket.getEndOfValidity();
        }
        return result + "'}";
    }

    public static String status2jsonString(Boolean isSuccessful) {
        if (isSuccessful) {
            return messageSuccessful;
        }
        return messageUnknownProblem;


    }


}
