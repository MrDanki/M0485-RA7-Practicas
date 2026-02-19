package main;
import model.Amount;
public interface Payable {
    boolean canClientPay(Amount toPay);
}
