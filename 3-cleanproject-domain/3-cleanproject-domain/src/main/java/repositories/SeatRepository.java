package repositories;

import businessObjects.Hall;
import businessObjects.Seat;

import java.util.ArrayList;

public interface SeatRepository {




    Seat findSeatByID(String seat);

    ArrayList <Seat>  findSeatsByHallName(String hallName);
}
