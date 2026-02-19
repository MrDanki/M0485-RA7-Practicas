package model;
import main.Payable;
import static assets.AmountManager.*;
import static assets.ColorManager.*;
public class Client extends Person implements Payable{
    // VARIABLES
    public String ID;
    public Amount balance;
    // CONSTRUCTOR
    public Client(String name,String ID,Amount balance){
        super(name);
        this.ID = ID;
        this.balance = balance;
    }
    // GETTER
    public String getID(){
        return ID;
    }
    public Amount getBalance(){
        return balance;
    }
    // SETTER
    public void setID(String ID){
        this.ID = ID;
    }
    public void setBalance(Amount balance){
        this.balance = balance;
    }
    @Override
    public boolean canClientPay(Amount toPay){
        Amount toPayInDollar = convertToDollar(toPay);
        Amount myBallance = convertToDollar(balance);
        if(myBallance.getValue()>= toPayInDollar.getValue()){
            balance.setValue(myBallance.getValue()-toPayInDollar.getValue());
            System.out.println(GREEN+"Transaction Successfull"+RESET);
            return true;
        }
        System.out.println(RED+name+" doesn't have enought Balance"+RESET); 
        return false;
    }
}
