public class Price {
    private double amount;
    private Currency currency;

    public Price (){
        this.amount =0;
        this.currency=Currency.EURO;
    }
    public Price (double amount) throws Exception {
        this.amount =checkAmountValue(amount);
        this.currency=Currency.EURO;
    }
    public void setCurrency(Currency currency){
        this.currency=currency;
    }
    public void setAmount(double amount) throws Exception {
        this.amount =checkAmountValue(amount);
    }

    private double checkAmountValue(double value) throws Exception{
        if (value<0){
            throw new Exception("Invalid price");
        }
        return value;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString(){
        return String.valueOf(amount).substring(0,4)+" "+currency.getSymbol();
    }

}
