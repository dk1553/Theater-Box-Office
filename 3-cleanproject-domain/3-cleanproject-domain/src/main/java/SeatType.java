public enum SeatType {
    ORCHESTRA_LEFT(1.6),
    ORCHESTRA_RIGHT(1.8),
    ORCHESTRA_CENTER(1.6),
    MEZZANINE_LEFT(1.2),
    MEZZANINE_RIGHT(1.4),
    MEZZANINE_CENTER(1.2),
    BALCONY(1);

    private double priceCoefficient;
    private SeatType(double priceCoefficient){
        this.priceCoefficient=priceCoefficient;
    }
}
