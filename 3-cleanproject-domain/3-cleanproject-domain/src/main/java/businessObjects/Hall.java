package businessObjects;

public class Hall {
    private final String hallName;
    private final int guestLimit;
    private final int numberOfSeatsOrchestraLeft; //numberOfSeatsOrchestraLeft
    private final int numberOfSeatsOrchestraRight; //numberOfSeatsOrchestraRight
    private final int numberOfSeatsOrchestraCenter; //numberOfSeatsOrchestraCenter
    private final int numberOfSeatsMezzanineRight; //numberOfSeatsMezzanineRight
    private final int numberOfSeatsMezzanineLeft; //numberOfSeatsMezzanineLeft
    private final int numberOfSeatsMezzanineCenter; //numberOfSeatsMezzanineCenter
    private final int numberOfSeatsBalcony; // numberOfSeatsBalcony

    private Seat[] seats;



    public Hall(String hallName, int numberOfSeatsOrchestraLeft, int numberOfSeatsOrchestraRight, int numberOfSeatsOrchestraCenter, int numberOfSeatsMezzanineRight, int numberOfSeatsMezzanineLeft, int numberOfSeatsMezzanineCenter, int numberOfSeatsBalcony ){
        this.hallName=hallName;
        this.numberOfSeatsOrchestraLeft=numberOfSeatsOrchestraLeft;
        this.numberOfSeatsOrchestraRight=numberOfSeatsOrchestraRight;
        this.numberOfSeatsOrchestraCenter=numberOfSeatsOrchestraCenter;
        this.numberOfSeatsMezzanineRight=numberOfSeatsMezzanineRight;
        this.numberOfSeatsMezzanineLeft=numberOfSeatsMezzanineLeft;
        this.numberOfSeatsMezzanineCenter=numberOfSeatsMezzanineCenter;
        this.numberOfSeatsBalcony=numberOfSeatsBalcony;
        this.guestLimit =numberOfSeatsOrchestraLeft+numberOfSeatsOrchestraRight+numberOfSeatsOrchestraCenter+numberOfSeatsMezzanineRight+numberOfSeatsMezzanineLeft+numberOfSeatsMezzanineCenter+numberOfSeatsMezzanineCenter+numberOfSeatsBalcony;
        seats= new Seat[guestLimit];

        int a=numberOfSeatsOrchestraLeft;
        int b=numberOfSeatsOrchestraRight;
        int c=numberOfSeatsOrchestraCenter;
        int d=numberOfSeatsMezzanineRight;
        int e=numberOfSeatsMezzanineLeft;
        int f=numberOfSeatsMezzanineCenter;
        int g=numberOfSeatsBalcony;

        for (int i=0; i<a;i++){
            seats[i]= new Seat(hallName,SeatType.ORCHESTRA_LEFT);
        }
        for (int i=a; i<a+b;i++){
            seats[i]= new Seat(hallName,SeatType.ORCHESTRA_RIGHT);
        }
        for (int i=a+b; i<a+b+c;i++){
            seats[i]= new Seat(hallName,SeatType.ORCHESTRA_CENTER);
        }
        for (int i=a+b+c; i<a+b+c+d;i++){
            seats[i]= new Seat(hallName,SeatType.MEZZANINE_RIGHT);
        }
        for (int i=a+b+c+d; i<a+b+c+d+e;i++){
            seats[i]= new Seat(hallName,SeatType.MEZZANINE_LEFT);
        }
        for (int i=a+b+c+d+e; i<a+b+c+d+e+f;i++){
            seats[i]= new Seat(hallName,SeatType.MEZZANINE_CENTER);
        }
        for (int i=a+b+c+d+e+f; i<guestLimit;i++){
            seats[i]= new Seat(hallName,SeatType.BALCONY);
        }

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

    public Seat[] getSeats() {
        return seats;
    }
}
