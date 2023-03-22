package businessObjects;

import businessObjects.Hall;

import java.util.ArrayList;

public final class TheaterBuilding {
    private final ArrayList<Hall> halls;

    public TheaterBuilding() {
        this.halls=new ArrayList<>();
        Hall smallHall= new Hall("Kleine Halle", 10,10,10,10,10,10,10);
        Hall bigHall = new Hall("Große Halle",10,10,10,15,20,15,20);
        this.halls.add(smallHall);
        this.halls.add(bigHall);

    }

    public Hall findHallByName(String name) {
        System.out.println("-------------"+ halls.size());
       for (Hall hall:halls){
           if (hall.getHallName().equalsIgnoreCase(name)){
               return hall;
           }
       }
       return null;
    }

    public Seat findSeatByName(String seatID) {
        for (Hall hall:halls){
            for (Seat seat:hall.getSeats()){
            if (seat.getSeatID().equalsIgnoreCase(seatID)){
                return seat;
             }
            }
        }
        return null;
    }
}
