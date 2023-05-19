package enums;

public enum SeatType {
    ORCHESTRA_LEFT(1.6, "ORCHESTRA_LEFT"),
    ORCHESTRA_RIGHT(1.6, "ORCHESTRA_RIGHT"),
    ORCHESTRA_CENTER(1.8, "ORCHESTRA_CENTER"),
    MEZZANINE_LEFT(1.2, "MEZZANINE_LEFT"),
    MEZZANINE_RIGHT(1.2, "MEZZANINE_RIGHT"),
    MEZZANINE_CENTER(1.4, "MEZZANINE_CENTER"),
    BALCONY(1, "BALCONY");

    private double priceCoefficient;
    private String typeName;

    private SeatType(double priceCoefficient, String typeName) {
        this.priceCoefficient = priceCoefficient;
        this.typeName = typeName;
    }

    public double getPriceCoefficient() {
        return priceCoefficient;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
