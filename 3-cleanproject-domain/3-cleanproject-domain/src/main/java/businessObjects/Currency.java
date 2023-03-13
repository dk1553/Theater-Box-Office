package businessObjects;

public enum Currency {
    EURO ('€'),
    DOLLAR ('$');

    private Character symbol;

    private Currency(char symbol){
        this.symbol=symbol;
    }

    public Character getSymbol() {
        return symbol;
    }
}
