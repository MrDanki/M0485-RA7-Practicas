package assets;
// IMPORTS
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
// CLASSES
import model.Product;
import model.Amount;
import static main.Main.*;
import static assets.ColorManager.*;
import static assets.LogicManager.*;
// MAIN
public class ProductManager{
    // GLOBALS
    public static double expiredDiscount = 0.6; // -40%
    public static String lastPID = "P000";
    // PRODUCT ARRAYLIST
    public static ArrayList<String> product = new ArrayList();
    // PRODUCT LOADER
    public static void productLoader(){
        Random random = new Random();
        productosDICCreator();
        // ID - Name - SellPrice - ShopPrice - avaliable - stock - totalProducts - expired
        for(int i=0; i<15; i++){
            lastPIDManager();
            String pr = getRandomProduct();
            double randomPrice = randomPrice(pr);
            int rc = random.nextInt(divisas.size()+1);
            String c;
            int pi;
            switch(rc){
                case 1:c="$";pi=1; break;
                case 2:c="E";pi=1; break;
                case 3:c="P";pi=20; break;
                case 4:c="Y";pi=40; break;
                default: c="$";pi=1;break;
            }
            Amount sellPrice = new Amount(round2Decimals(randomPrice*pi),c);
            Amount shopPrice = new Amount(round2Decimals(sellPrice.getValue()*2),c);
            int totalProducts = random.nextInt(50,100);
            productos.add(new Product(lastPID,pr,sellPrice,shopPrice,0,totalProducts,false));
        }
    }
    public static void lastPIDManager(){
          lastPID = String.format("P%03d",productos.size());
    }
    public static String[]food ={
        "Fresas","Hamburgesas","Peras","Manzanas","Pinas","Melones",
        "Helados","Doritos","Tacos","Chili","Platanos","Hamburgesas",
        "Yogurts","Kiwi","Patatas","Gominolas","Mangos","Filetes","Proteinas",
        "Arroz","Tomates","Kimchi","Borsh","Pelmeni","Donuts","Mortadela","Fuet"
    };
    public static String[]drink ={
        "Vodka","Ron","Wisky","Tabaco","Monster","Kvass",
        "Pepsi","RedBull","Fentanilo","Vino","Agua","Limonchelo","Nutella"
    };
    public static String[]guns ={
        "AK-47","DeasertEagle","RPG-7","RSD-220","Thompson"
    };
    public static String[]strike ={
        "SU-25","Me-262","A-10"
    };
    public static String[]vehicles ={
        "T-80BVM","Lada2100","T-90M2","T-15","T-34-85","Kamaz6250"
    };
    
    public static void productosDICCreator(){ 
        product.addAll(Arrays.asList(food));
        product.addAll(Arrays.asList(drink));
        product.addAll(Arrays.asList(guns));
        product.addAll(Arrays.asList(strike));
        product.addAll(Arrays.asList(vehicles));
    }
    
    public static double randomPrice(String rp){
        Random random = new Random();
        double pr = 0;
        for(String p : food){if(p.equals(rp)){pr = round2Decimals(random.nextInt(1,10)); break;}}
        for(String p : drink){if(p.equals(rp)){pr = round2Decimals(random.nextInt(50,100)); break;}}
        for(String p : guns){if(p.equals(rp)){pr = round2Decimals(random.nextInt(100,200)); break;}}
        for(String p : strike){if(p.equals(rp)){pr = round2Decimals(random.nextInt(100,200)*100); break;}}
        for(String p : vehicles){if(p.equals(rp)){pr = round2Decimals(random.nextInt(100,200)*100); break;}}
        return pr;
    }
    public static boolean isInventoryEmpty(){
        if(inventory.isEmpty()){
            System.out.println(RED+"Shop Inventory is Empty"+RESET);
            return true;
        }
        return false;
    }
    public static ArrayList<String> randomizedProducts = new ArrayList();
    public static String getRandomProduct(){
        Random random = new Random();
        String newProduct;
        int prd = random.nextInt(product.size());
        newProduct = product.get(prd);
        if(randomizedProducts.contains(newProduct)){
            return getRandomProduct();
        }
        randomizedProducts.add(newProduct);
        return newProduct; 
    }
    // SEARCHER
    public static Product searchInProductsReturnP(String input){
        for(Product p : productos){
            if(input.equals(p.getName())){return p;}
            if(input.equals(p.getID())){return p;}
        }
        System.out.println(RED+"Product not found"+RESET);
        return null;
    }
    public static Product searchInInventroyReturnP(String input){
        for(Product i : inventory){
            if(input.equals(i.getName())){return i;}
            if(input.equals(i.getID())){return i;}
        }
        System.out.println(RED+"Product not found"+RESET);
        return null;
    }
    // CHECK INVENTORY
    public static boolean hasProductInventory(Product p){
        for(Product i : inventory){
            if(i.getID().equals(p.getID())){ return true;}
        }
        return false;
    }
    // CHECK FOR STOCK / TP
    public static boolean hasProductDisponible(Product p,int toBuy){
        for(Product pr : productos){
            if(pr.getID().equals(p.getID())){
                if(pr.getTotalProducts() > toBuy){return true;}
            }
        }
        System.out.println(RED+"No Products Disponible"+RESET);
        return false;
    }
    public static boolean hasStockDisponible(Product p,int toBuy){
        for(Product pr : productos){
            if(pr.getID().equals(p.getID())){
                if(pr.getStock() > 0){return true;}
            }
        }
        System.out.println(RED+"No Stock Disponible"+RESET);
        return false;
    }
    
    // SET EXPIRED PRICE
    public static void setExpiredPrice(Product p){
        double price = p.getShopPrice().getValue();
        String currency = p.getShopPrice().getCurrency();
        p.setShopPrice(new Amount(price*expiredDiscount,currency));
         
    }
    // UNIVERSAL PRINTER - P0000000 -> P/I = Productos / Inventory -> 0/1 variable
    public static void printInDisplay(String code){
        char L1 = code.charAt(0);
        switch(L1){
            case 'P':
                System.out.println("== "+PURPLE+"PRODUCTS"+RESET+" ==");
                for(Product p : productos){
                    String ID = " ID: "+PURPLE+p.getID()+RESET;
                    if(code.charAt(1)=='0'){ID = "";}
                    String name = " / Name: "+PURPLE+p.getName()+RESET;
                    if(code.charAt(1)=='0'){name = "";}
                    String sellPrice = " / Seller Price: "+PURPLE+p.getSellerPrice().toString()+RESET;
                    if(code.charAt(3)=='0'){sellPrice = "";}
                    String shopPrice = " / Shop Price: "+PURPLE+p.getShopPrice().toString()+RESET;
                    if(code.charAt(4)=='0'){shopPrice = "";}
                    String stock = " / Stock: "+PURPLE+p.getStock()+RESET;
                    if(code.charAt(5)=='0'){stock = "";}
                    String totalProducts = " / Total Products: "+PURPLE+p.getTotalProducts()+RESET;
                    if(code.charAt(6)=='0'){totalProducts = "";}
                    String expired = " / Expired: "+invertedBooleanPainter(p.getExpired());
                    if(code.charAt(7)=='0'){expired = "";}
                    System.out.println("- "+ID+name+sellPrice+shopPrice+stock+totalProducts+expired);
                }
                break;
            case 'I':
                if(isInventoryEmpty()){return;}
                System.out.println("== "+BLUE+"SHOP INVENTORY"+RESET+" ==");
                for(Product i : inventory){
                    String ID = " ID: "+BLUE+i.getID()+RESET;
                    if(code.charAt(1)=='0'){ID = "";}
                    String name = " / Name: "+BLUE+i.getName()+RESET;
                    if(code.charAt(1)=='0'){name = "";}
                    String sellPrice = " / Seller Price: "+BLUE+i.getSellerPrice().toString()+RESET;
                    if(code.charAt(3)=='0'){sellPrice = "";}
                    String shopPrice = " / Shop Price: "+BLUE+i.getShopPrice().toString()+RESET;
                    if(code.charAt(4)=='0'){shopPrice = "";}
                    String stock = " / Stock: "+BLUE+i.getStock()+RESET;
                    if(code.charAt(5)=='0'){stock = "";}
                    String totalProducts = " / Total Products: "+BLUE+i.getTotalProducts()+RESET;
                    if(code.charAt(6)=='0'){totalProducts = "";}
                    String expired = " / Expired: "+invertedBooleanPainter(i.getExpired());
                    if(code.charAt(7)=='0'){expired = "";}
                    System.out.println("- "+ID+name+sellPrice+shopPrice+stock+totalProducts+expired);
                }
                break;
            default: System.out.println(RED+"PRINT ERROR"+RESET); break;
        }
    }
}
