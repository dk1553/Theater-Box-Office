import java.util.ArrayList;

public class ShowProgram {
    public static ArrayList<Event> show (TheaterService service) {
        return  service.getTheaterProgram().getEventList();  }
}
