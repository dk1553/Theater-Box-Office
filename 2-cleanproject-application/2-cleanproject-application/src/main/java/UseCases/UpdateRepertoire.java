package UseCases;
import businessObjects.Performance;
import Service.TheaterService;

import java.util.ArrayList;

public class UpdateRepertoire {
    public static void addPerformances (TheaterService service, ArrayList<Performance> performances) {
        for (Performance performance:performances){
            service.getRepertoire().addPerformance(performance);
        }

    }
}
