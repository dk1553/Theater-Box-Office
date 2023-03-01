import java.util.ArrayList;

public class ShowRepertoire {
    public static ArrayList<Performance> show (TheaterService service) {
        return  service.getRepertoire().getPerformances();
    }




}
