import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import io.javalin.http.Context;
import netscape.javascript.JSObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public final class EventController {
    private EventController(){}
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
}


