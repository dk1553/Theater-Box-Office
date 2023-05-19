package utilities;

public class SeatIdentifier {

    private static int seatID = 0;

    public static int getNewSeatID() {
        seatID++;
        return seatID;
    }

}
