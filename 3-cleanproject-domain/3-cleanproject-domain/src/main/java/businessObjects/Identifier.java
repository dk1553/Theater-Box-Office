package businessObjects;

public class Identifier {
    private static int performanceID=0;
    private static int eventID=0;
    private static int seatID=0;
    private static int ticketID=0;


    public static int getNewPerformanceID() {
        performanceID++;
        return performanceID;
    }

    public static int getNewEventID() {
        eventID++;
        return eventID;
    }

    public static int getNewSeatID() {
        seatID++;
        return seatID;
    }

    public static int getNewTicketID() {
        ticketID++;
        return ticketID;
    }


}
