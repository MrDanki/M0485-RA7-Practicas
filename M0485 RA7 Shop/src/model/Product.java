package model;
public class Product {
    // VARIABLES
    public String ID;
    public String name;
    public Amount sellerPrice;
    public Amount shopPrice;
    public int stock;
    public int totalProducts;
    public boolean expired;
    // CONSTRUCTOR
    public Product(String ID,String name,Amount sellerPrice,Amount shopPrice,int stock,int totalProducts,boolean expired){
        this.ID = ID;
        this.name = name;
        this.sellerPrice = sellerPrice;
        this.shopPrice = shopPrice;
        this.stock = stock;
        this.totalProducts = totalProducts;
        this.expired = expired;
    }
    // GETTER
    public String getID(){
        return ID;
    }
    public String getName(){
        return name;
    }
    public Amount getSellerPrice(){
        return sellerPrice;
    }
    public Amount getShopPrice(){
        return shopPrice;
    }
    public int getStock(){
        return stock;
    }
    public int getTotalProducts(){
        return totalProducts;
    }
    public boolean getExpired(){
        return expired;
    }
    // SETTER
    public void setID(String ID){
        this.ID = ID;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSellerPrice(Amount sellerPrice){
        this.sellerPrice = sellerPrice;
    }
    public void setShopPrice(Amount shopPrice){
        this.shopPrice = shopPrice;
    }
    public void setStock(int stock){
        this.stock = stock;
    }
    public void setTotalProducts(int totalProducts){
        this.totalProducts = totalProducts;
    }
    public void setExpired(boolean expired){
        this.expired = expired;
    }
}
