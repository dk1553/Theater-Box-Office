import businessObjects.Performance;
import junit.framework.TestSuite;
import persistence.PerformanceRepositoryJDBC;
import repositories.PerformanceRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class TestPerformanceUniqueness {


    @org.junit.Test
    public void duplicatesDetection() {
        //try to find duplicates in performance repository
        PerformanceRepository repository= new PerformanceRepositoryJDBC();
        List<String> performanceList = new ArrayList<>();
        for (Performance p:repository.findAllPerformances()){
            performanceList.add(p.getName());
        }
        Set<String> performanceSet = new HashSet<String>(performanceList);

        if(performanceSet.size() < performanceList.size()){
           assertTrue("Duplicates are detected",false);
        }
    }
    @org.junit.Test
    public void creationOfDuplicates() {
        //try to add duplicates to performance repository
        PerformanceRepository repository= new PerformanceRepositoryJDBC();
        if (!repository.isEmpty()){
            ArrayList<Performance> performances= new ArrayList<>();
            performances.add(repository.findAllPerformances().get(0));
            performances.add(repository.findAllPerformances().get(0));
            Boolean status=repository.addPerformances(performances);
            assertEquals("Duplicates could be added to repository",false, status );
        }
    }
    
}
