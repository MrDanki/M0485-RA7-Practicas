package model;
public class Amount {
    // VARIABLES
    private double value;
    private String currency;
    // CONSTRUCTOR
    public Amount(double value, String currency){
        this.value = value;
        this.currency = currency;
    }
    // GETTER
    public double getValue(){
        return value;
    }
    public String getCurrency(){
        return currency;
    }
    // SETTER
    public void setValue(double value){
        this.value = value;
    }
    public void setCurrency(String currency){
        this.currency = currency;
    }
    @Override
    public String toString(){
        return value + currency;
    }
}
