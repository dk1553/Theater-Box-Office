import java.util.ArrayList;

public class TheaterProgram {
    private ArrayList <Event> eventList;
    public  TheaterProgram(){
        eventList= new ArrayList<>();
    }
    public void addEvent (Event event){
        eventList.add(event);
    }
    public ArrayList <Event> getEventList(){
        return eventList;
    }

    public void addSeveralEvents(ArrayList<Event> events) {
        eventList.addAll(events);
    }
}
