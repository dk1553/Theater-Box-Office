package repositories;

import businessObjects.Hall;
import businessObjects.Seat;

import java.util.ArrayList;

public interface HallRepository {
    Hall findHallByName(String hall);
    ArrayList<Hall> getAllHalls();

}
