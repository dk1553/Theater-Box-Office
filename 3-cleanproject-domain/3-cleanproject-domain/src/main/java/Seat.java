public class Seat {
    private int seatID;
    private String hall;
    private SeatType type;
    private int seatNumber;

    public Seat (int seatID, String hall, SeatType type){
        this.seatID=seatID;
        this.hall=hall;
        this.type=type;
        //change seatNumber to seatLine and seatNumberInLine
        this.seatNumber=seatID+1;

    }

    public int getSeatID() {
        return seatID;
    }

    public String getHall() {
        return hall;
    }

    public SeatType getType() {
        return type;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}
