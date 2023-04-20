package businessObjects;

public enum Currency {
    EURO("€"),
    DOLLAR("$");

    private String symbol;

    private Currency(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
