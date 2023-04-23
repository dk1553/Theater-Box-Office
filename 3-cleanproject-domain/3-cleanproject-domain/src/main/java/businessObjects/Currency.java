package businessObjects;

public enum Currency {
    EURO("€"),
    DOLLAR("$");

    private String symbol;

    Currency(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
