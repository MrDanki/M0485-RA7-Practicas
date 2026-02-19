package model;
import java.util.ArrayList;
import static assets.SaleManager.*;
public class Sale {
    // VARIABLES
    public String ID;
    public Client client;
    public ArrayList<Product> products;
    public ArrayList<Integer> quantity;
    public Amount amount;
    // CONSTRUCTOR
    public Sale(String ID,Client client,ArrayList<Product> products,ArrayList<Integer> quantity,Amount amount){
        this.ID = ID;
        this.client = client;
        this.products = products;
        this.quantity = quantity;
        this.amount = amount;
    }
    // GETTER
    public String getID(){
        return ID;
    }
    public Client getClient(){
        return client;
    }
    public ArrayList<Product> getProducts(){
        return products;
    }
    public ArrayList<Integer> getQuantity(){
        return quantity;
    }
    public Amount getAmount() {
        return amount;
    }
    // SETTER
    public void setID(String ID){
        this.ID = ID;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
     public void setQuantity(ArrayList<Integer> quantity) {
        this.quantity = quantity;
    }
    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}
