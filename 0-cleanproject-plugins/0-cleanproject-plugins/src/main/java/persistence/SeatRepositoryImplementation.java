package persistence;

import businessObjects.Hall;
import businessObjects.Seat;
import businessObjects.SeatType;
import repositories.HallRepository;
import repositories.SeatRepository;

import java.util.ArrayList;

public class SeatRepositoryImplementation implements SeatRepository {
    private final ArrayList<Seat> seats;

    public SeatRepositoryImplementation(HallRepository hallRepository) {
        this.seats = new ArrayList<>();

        for (Hall hall : hallRepository.getAllHalls()) {

            int a = hall.getNumberOfSeatsOrchestraLeft();
            int b = hall.getNumberOfSeatsOrchestraRight();
            int c = hall.getNumberOfSeatsOrchestraCenter();
            int d = hall.getNumberOfSeatsMezzanineRight();
            int e = hall.getNumberOfSeatsMezzanineLeft();
            int f = hall.getNumberOfSeatsMezzanineCenter();
            int g = hall.getNumberOfSeatsBalcony();

            for (int i = 0; i < a; i++) {
                seats.add(new Seat(hall.getHallName(), SeatType.ORCHESTRA_LEFT));
            }
            for (int i = a; i < a + b; i++) {
                seats.add(new Seat(hall.getHallName(), SeatType.ORCHESTRA_RIGHT));
            }
            for (int i = a + b; i < a + b + c; i++) {
                seats.add(new Seat(hall.getHallName(), SeatType.ORCHESTRA_CENTER));
            }
            for (int i = a + b + c; i < a + b + c + d; i++) {
                seats.add(new Seat(hall.getHallName(), SeatType.MEZZANINE_RIGHT));
            }
            for (int i = a + b + c + d; i < a + b + c + d + e; i++) {
                seats.add(new Seat(hall.getHallName(), SeatType.MEZZANINE_LEFT));
            }
            for (int i = a + b + c + d + e; i < a + b + c + d + e + f; i++) {
                seats.add(new Seat(hall.getHallName(), SeatType.MEZZANINE_CENTER));
            }
            for (int i = a + b + c + d + e + f; i < hall.getGuestLimit(); i++) {
                seats.add(new Seat(hall.getHallName(), SeatType.BALCONY));
            }
        }


    }

    @Override
    public ArrayList<Seat> findSeatsByHallName(String hallName) {
        ArrayList<Seat> result = new ArrayList<>();
        for (Seat seat : seats) {
            if (seat.getHall().equalsIgnoreCase(hallName)) {
                result.add(seat);
            }
        }
        return result;
    }

    @Override
    public Seat findSeatByID(String seatID) {

        for (Seat seat : seats) {
            if (seat.getSeatID().equalsIgnoreCase(seatID)) {
                return seat;
            }
        }
        return null;
    }
}
