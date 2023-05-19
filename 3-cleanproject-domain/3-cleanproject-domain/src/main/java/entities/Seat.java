package entities;

import enums.SeatType;
import services.SeatIdentifier;

public class Seat {
    private String seatID;
    private String hall;
    private SeatType type;
    private String seatNumber;

    public Seat(String hall, SeatType type) {
        this.seatID = String.valueOf(SeatIdentifier.getNewSeatID());
        this.hall = hall;
        this.type = type;
        //change seatNumber to seatLine and seatNumberInLine
        this.seatNumber = seatID;

    }

    public String getSeatID() {
        return seatID;
    }

    public String getHall() {
        return hall;
    }

    public SeatType getType() {
        return type;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
}
