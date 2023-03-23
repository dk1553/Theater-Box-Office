package mapper;

import businessObjects.Performance;
import resources.PerformanceResource;

import java.util.ArrayList;

public class PerformanceMapper {
    public Performance map(final PerformanceResource performanceResource) throws Exception {
        if (performanceResource!=null){
            return new Performance(performanceResource.getName(),performanceResource.getDescription());
        }else{
            return null;
        }


    }

    public ArrayList<Performance> map(ArrayList<PerformanceResource> performanceResources) throws Exception {
        if ((performanceResources!=null)&&(!performanceResources.isEmpty())){
            ArrayList<Performance> performances=new ArrayList<>();
            for (PerformanceResource performanceResource:performanceResources){
                performances.add(map(performanceResource));
            }
            return performances;
        }else{
            return null;
        }

    }
}
