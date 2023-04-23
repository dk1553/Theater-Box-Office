package rest.converters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import resources.EventResource;
import resources.PerformanceResource;
import resources.TicketResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class JsonService {

    static final String messageSuccessful = "{'message':'Successful'}";
    static final String messageDBisEmpty = "{'message':'Database is empty'}";
    static final String messageLogin = "'message':'Please log in to use this function!'";
    static final String messageNotCompleted = "{'message':'Your purchase could not be completed'}";
    static final String messageUnknownProblem = "{'message':'Sorry! Your purchase could not be completed. Unknown problem ####'}";


    public static String eventResource2jsonString(EventResource event) {
        if (event == null) {
            return messageDBisEmpty;
        }
        String result = "{'id':'" + event.getEventID() + "','performance':'" + event.getPerformance() + "','data':'" + event.getDate() + "','time':'" + event.getTime() + "','hall':'" + event.getHall() + ",\n{'tickets':[\n";
        for (TicketResource ticket : event.getTicketResources()) {
            String status;
            if (ticket.isBooked()) {
                status = "booked";
            } else {
                status = "available";
            }
            result = result + "{'id':'" + ticket.getId() + "','seat number':'" + ticket.getSeat() + "','seat number':'" + ticket.getSeat() + "','status':'" + status + "','preis':'" + ticket.getPrice() + " " + ticket.getCurrency() + "'},\n";
        }
        return result.substring(0, result.length() - 2) + "]}";


    }

    public static String eventResourceList2jsonString(List<EventResource> events) {
        if ((Objects.isNull(events)) || (events.isEmpty())) {
            return messageDBisEmpty;
        }
        String result = "{'events':[";
        for (EventResource event : events) {
            if (event != null) {
                if ((event.getTicketResources() != null) && (!event.getTicketResources().isEmpty())) {
                    result = result + "{'id':'" + event.getEventID() + "','performance':'" + event.getPerformance() + "','date':'" + event.getDate() + "','time':'" + event.getTime() + "','available tickets':'" + event.getNumberOfAvailableSeats() + "','booked tickets':'" + event.getNumberOfBookedSeats() + "'},";

                } else {
                    result = result + "{'id':'" + event.getEventID() + "','performance':'" + event.getPerformance() + "','date':'" + event.getDate() + "','time':'" + event.getTime() + "','available tickets':'" + "no data" + "','booked tickets':'" + "no data" + "'},";

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

    public static String loginMessage() {
        return messageLogin;

    }

    public static String performanceResourceList2jsonString(List<PerformanceResource> performances) {
        if (performances.isEmpty()) {
            return messageDBisEmpty;
        }
        String result = "{'performances':[";
        for (PerformanceResource performance : performances) {
            if (performance != null) {
                result = result + "{'name':'" + performance.getName() + "','description':'" + performance.getDescription() + "'},";
            }
        }
        return result.substring(0, result.length() - 1) + "]}";

    }

    public static String boughtTicket2jsonString(TicketResource ticket) {
        if (ticket == null) {
            return messageNotCompleted;
        }
        return "{'id':'" + ticket.getId() + "','event':'" + ticket.getEventID() + "','seat':'" + ticket.getSeat() + "','price':'" + ticket.getPrice() + "','is booked':'" + ticket.isBooked() + "','validation code':'" + ticket.getValidationCode() + "'}";

    }

    public static List<EventResource> json2EventResourceList(String contextBody) throws JSONException {
        JSONObject performanceJson = new JSONObject(contextBody);
        JSONArray jsonArrayEvents = performanceJson.getJSONArray("program");
        List<EventResource> eventResources = new ArrayList<>();
        for (int i = 0; i < jsonArrayEvents.length(); i++) {
            JSONObject pJ = jsonArrayEvents.getJSONObject(i);
            try {
                EventResource event = new EventResource(pJ.getString("performance"), pJ.getString("date"), pJ.getString("time"), pJ.getString("hall"), pJ.getString("basic price"));
                eventResources.add(event);
                return eventResources;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return Collections.emptyList();

    }

    public static List<PerformanceResource> json2PerformanceList(String contextBody) throws JSONException {
        JSONObject performanceJson = new JSONObject(contextBody);
        JSONArray jsonArrayPerformances = performanceJson.getJSONArray("performances");
        List<PerformanceResource> performanceList = new ArrayList<>();
        for (int i = 0; i < jsonArrayPerformances.length(); i++) {
            JSONObject pJ = jsonArrayPerformances.getJSONObject(i);
            PerformanceResource performanceResource = new PerformanceResource(pJ.getString("name"), pJ.getString("description"));
            performanceList.add(performanceResource);
        }
        return performanceList;
    }

    public static String status2jsonString(Boolean isSuccessful) {
        if (isSuccessful) {
            return messageSuccessful;
        }

        return messageUnknownProblem;


    }

    public static String[] getCredentials(String json) throws JSONException {
        JSONObject userJson = new JSONObject(json);
        JSONObject pJ = userJson.getJSONObject("admin");
        String[] creds = new String[2];
        creds[0] = pJ.getString("username");
        creds[1] = pJ.getString("password");
        return creds;
    }

    public static String[] getUsername(String json) throws JSONException {
        JSONObject userJson = new JSONObject(json);
        JSONObject pJ = userJson.getJSONObject("user");
        String[] username = new String[2];
        username[0] = pJ.getString("first name");
        username[1] = pJ.getString("last name");
        return username;
    }
}
