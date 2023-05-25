package services;

public class SeatIdentifier {
    //kein Domain Service, nur ein Hilfsservice

    private static int seatID = 0;
    private SeatIdentifier(){
        throw new IllegalStateException("Utility class");
    }

    public static int getNewSeatID() {
        seatID++;
        return seatID;
    }

}
