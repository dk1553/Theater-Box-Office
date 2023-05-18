package businessObjects;

import java.math.BigDecimal;

public class Price {
    final  private BigDecimal amount;
    final private Currency currency;

    public Price() {
        //default price is 1000 Euro
        this.amount = new BigDecimal("1000");
        this.currency = Currency.EURO;
    }

    public Price(BigDecimal amount) throws Exception {
        this.amount = checkAmountValue(amount);
        this.currency = Currency.EURO;
    }

    public Price(String amount) throws Exception {
        this.amount = checkAmountValue(parseAmount(amount));
        this.currency = Currency.EURO;
    }
    private BigDecimal parseAmount(String amount){
        try {
           return BigDecimal.valueOf(Long.parseLong(amount));
        } catch (Exception e) {
            try {
               return  BigDecimal.valueOf(Double.parseDouble(amount));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private BigDecimal checkAmountValue(BigDecimal value) throws Exception {
        if (value.doubleValue() < 0) {
            throw new Exception("Invalid price");
        }
        return value;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }

}
