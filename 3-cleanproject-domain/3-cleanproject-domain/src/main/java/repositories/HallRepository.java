package repositories;

import entities.Hall;

import java.util.List;

public interface HallRepository {
    Hall findHallByName(String hall);
    int countSeatsByHall(Hall hall);
    List<Hall> findAllHalls();

}
