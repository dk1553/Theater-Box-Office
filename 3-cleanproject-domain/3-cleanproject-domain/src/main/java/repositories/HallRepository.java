package repositories;

import businessObjects.Hall;

import java.util.List;

public interface HallRepository {
    Hall findHallByName(String hall);

    List<Hall> getAllHalls();

}
