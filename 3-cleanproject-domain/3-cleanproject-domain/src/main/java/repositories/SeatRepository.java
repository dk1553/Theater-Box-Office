package repositories;

import businessObjects.Seat;

import java.util.List;

public interface SeatRepository {

    Seat findSeatByID(String seat);

    List<Seat> findSeatsByHallName(String hallName);
}
