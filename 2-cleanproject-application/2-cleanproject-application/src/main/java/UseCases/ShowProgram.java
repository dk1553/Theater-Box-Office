package UseCases;
import Service.TheaterService;
import java.util.ArrayList;
import businessObjects.Event;

public class ShowProgram {
    public static ArrayList<Event> show (TheaterService service) {
        return  service.getTheaterProgram().getEventList();  }
}
