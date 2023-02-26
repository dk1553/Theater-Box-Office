public class Price {
    private int amount;
    private Currency currency;

    public Price (){
        this.amount =0;
        this.currency=Currency.EURO;
    }
    public Price (int amount) throws Exception {
        this.amount =checkAmountValue(amount);
        this.currency=Currency.EURO;
    }
    public void setCurrency(Currency currency){
        this.currency=currency;
    }
    public void setAmount(int amount) throws Exception {
        this.amount =checkAmountValue(amount);
    }

    private int checkAmountValue(int value) throws Exception{
        if (value<0){
            throw new Exception("Invalid price");
        }
        return value;
    }
}
