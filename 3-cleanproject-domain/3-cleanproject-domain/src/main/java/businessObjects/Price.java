package businessObjects;

import java.math.BigDecimal;

public class Price {
    private BigDecimal amount;
    private Currency currency;

    public Price (){
        this.amount =new BigDecimal("0");
        this.currency=Currency.EURO;
    }
    public Price (BigDecimal amount) throws Exception {
        this.amount =checkAmountValue(amount);
        this.currency=Currency.EURO;
    }

    public Price (String amount) throws Exception {

        this.amount =checkAmountValue(BigDecimal.valueOf(Long.parseLong(amount)));
        this.currency=Currency.EURO;
    }
    public void setCurrency(Currency currency){
        this.currency=currency;
    }
    public void setAmount(BigDecimal amount) throws Exception {
        this.amount =checkAmountValue(amount);
    }

    private BigDecimal checkAmountValue(BigDecimal value) throws Exception{
        if (value.doubleValue()<0){
            throw new Exception("Invalid price");
        }
        return value;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
   public String toString(){
        return String.valueOf(amount);//.substring(0,4)+" "+currency.getSymbol();
    }

}
