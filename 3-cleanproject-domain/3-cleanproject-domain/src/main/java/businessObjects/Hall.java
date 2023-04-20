package businessObjects;

public class Hall {
    private final String hallName;
    private final int guestLimit;
    private final int numberOfSeatsOrchestraLeft;
    private final int numberOfSeatsOrchestraRight;
    private final int numberOfSeatsOrchestraCenter;
    private final int numberOfSeatsMezzanineRight;
    private final int numberOfSeatsMezzanineLeft;
    private final int numberOfSeatsMezzanineCenter;
    private final int numberOfSeatsBalcony;


    public Hall(String hallName, int numberOfSeatsOrchestraLeft, int numberOfSeatsOrchestraRight, int numberOfSeatsOrchestraCenter, int numberOfSeatsMezzanineRight, int numberOfSeatsMezzanineLeft, int numberOfSeatsMezzanineCenter, int numberOfSeatsBalcony) {
        this.hallName = hallName;
        this.numberOfSeatsOrchestraLeft = numberOfSeatsOrchestraLeft;
        this.numberOfSeatsOrchestraRight = numberOfSeatsOrchestraRight;
        this.numberOfSeatsOrchestraCenter = numberOfSeatsOrchestraCenter;
        this.numberOfSeatsMezzanineRight = numberOfSeatsMezzanineRight;
        this.numberOfSeatsMezzanineLeft = numberOfSeatsMezzanineLeft;
        this.numberOfSeatsMezzanineCenter = numberOfSeatsMezzanineCenter;
        this.numberOfSeatsBalcony = numberOfSeatsBalcony;
        this.guestLimit = numberOfSeatsOrchestraLeft + numberOfSeatsOrchestraRight + numberOfSeatsOrchestraCenter + numberOfSeatsMezzanineRight + numberOfSeatsMezzanineLeft + numberOfSeatsMezzanineCenter + numberOfSeatsMezzanineCenter + numberOfSeatsBalcony;

    }

    public String getHallName() {
        return hallName;
    }

    public int getGuestLimit() {
        return guestLimit;
    }

    public int getNumberOfSeatsOrchestraLeft() {
        return numberOfSeatsOrchestraLeft;
    }

    public int getNumberOfSeatsOrchestraRight() {
        return numberOfSeatsOrchestraRight;
    }

    public int getNumberOfSeatsOrchestraCenter() {
        return numberOfSeatsOrchestraCenter;
    }

    public int getNumberOfSeatsMezzanineRight() {
        return numberOfSeatsMezzanineRight;
    }

    public int getNumberOfSeatsMezzanineLeft() {
        return numberOfSeatsMezzanineLeft;
    }

    public int getNumberOfSeatsMezzanineCenter() {
        return numberOfSeatsMezzanineCenter;
    }

    public int getNumberOfSeatsBalcony() {
        return numberOfSeatsBalcony;
    }


}
