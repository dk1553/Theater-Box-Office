package services;

public class SeatIdentifier {
    //kein Domain Service, nur ein Hilfsservice

    private static int seatID = 0;

    public static int getNewSeatID() {
        seatID++;
        return seatID;
    }

}
