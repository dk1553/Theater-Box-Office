package rest.converters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import resources.EventResource;
import resources.PerformanceResource;

import java.util.ArrayList;
import java.util.List;

public class ConvertFromJsonService {
    public static List<EventResource> json2EventResourceList(String contextBody) throws JSONException {
        JSONObject performanceJson = new JSONObject(contextBody);
        JSONArray jsonArrayEvents = performanceJson.getJSONArray("program");
        List<EventResource> eventResources = new ArrayList<>();
        for (int i = 0; i < jsonArrayEvents.length(); i++) {
            JSONObject pJ = jsonArrayEvents.getJSONObject(i);
            EventResource event = new EventResource(pJ.getString("performance"), pJ.getString("date"), pJ.getString("time"), pJ.getString("hall"), pJ.getString("basic price"));
            eventResources.add(event);
        }
        return eventResources;

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
    public static String[] json2Credentials(String json) throws JSONException {
        JSONObject userJson = new JSONObject(json);
        JSONObject pJ = userJson.getJSONObject("admin");
        String[] creds = new String[2];
        creds[0] = pJ.getString("username");
        creds[1] = pJ.getString("password");
        return creds;
    }

    public static String[] json2Username(String json) throws JSONException {
        JSONObject userJson = new JSONObject(json);
        JSONObject pJ = userJson.getJSONObject("user");
        String[] username = new String[2];
        username[0] = pJ.getString("first name");
        username[1] = pJ.getString("last name");
        return username;
    }
}
