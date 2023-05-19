package persistence;

import entities.Hall;
import repositories.HallRepository;

import java.util.ArrayList;
import java.util.List;

public final class HallRepositoryImplementation implements HallRepository {
    private final List<Hall> halls;

    public HallRepositoryImplementation() {
        this.halls = new ArrayList<>();
        Hall smallHall = new Hall("Kleine Halle", 10, 10, 10, 10, 10, 10, 10);
        Hall bigHall = new Hall("Große Halle", 10, 10, 10, 15, 20, 15, 20);
        this.halls.add(smallHall);
        this.halls.add(bigHall);

    }

    public Hall findHallByName(String name) {
        for (Hall hall : halls) {
            if (hall.getHallName().equalsIgnoreCase(name)) {
                return hall;
            }
        }
        return null;
    }

    @Override
    public int countSeatsByHall(Hall hall) {
        return  hall.getNumberOfSeatsOrchestraLeft() +
                hall.getNumberOfSeatsOrchestraRight()
                + hall.getNumberOfSeatsOrchestraCenter()
                + hall.getNumberOfSeatsMezzanineRight()
                + hall.getNumberOfSeatsMezzanineLeft()
                + hall.getNumberOfSeatsMezzanineCenter()
                + hall.getNumberOfSeatsMezzanineCenter()
                + hall.getNumberOfSeatsBalcony();
    }

    @Override
    public List<Hall> findAllHalls() {
        return halls;
    }


}
