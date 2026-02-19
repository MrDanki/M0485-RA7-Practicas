package main;
// IMPORTS
import java.util.ArrayList;
import java.util.Scanner;
// CLASSES
import model.Product;
import model.Amount;
import model.Employee;
import model.Client;
import model.Sale;
import static assets.SaleManager.*;
import static assets.EmployeeManager.*;
import static assets.AmountManager.*;
import static assets.LogicManager.*;
import static assets.ProductManager.*;
import static assets.ClientManager.*;
import static assets.ColorManager.*;
// MAIN
public class Main {
    // GLOBAL ARRAYS
    public static ArrayList<Product>productos = new ArrayList<>();
    public static ArrayList<Product>inventory = new ArrayList<>();
    public static ArrayList<Amount>divisas = new ArrayList<>();
    public static ArrayList<Employee>empleados = new ArrayList<>();
    public static ArrayList<Client>clientes = new ArrayList<>();
    public static ArrayList<Sale>sales = new ArrayList<>();
    // GLOBAL VARIABLES
    public static Amount CASH = new Amount(1200,"$");
    public static boolean isLoaded = false;
    // MAIN
    public static void main(String[]args){
       Scanner scanner = new Scanner(System.in);
       amountLoader();
       productLoader();
       employeeLoader();
       clientLoader();
       Employee login;
       do{
            System.out.println("");
            System.out.println("== "+RED+"SHOP LOGIN"+RESET+" ==");
            System.out.print("EmployeeID: ");
            String ID = scanner.nextLine();
            if(ID.equals("enter")||ID.equals("skip")||ID.equals("load")){
                System.out.println(GREEN+"Login Correct"+RESET);
                System.out.println("Welcome to the Shop Manager "+RED+"MrDanki"+RESET);
                break;
            }
            System.out.print("Password: ");
            String password = scanner.nextLine();
            login = new Employee(searchEmployeeNameWithID(ID),ID,password);
       }while(!login.isLoginCorrect());
       printCash();
       String op;
       do{
           menu();
           op = scanner.nextLine();
           switch(op){
               case "0": System.out.println(RED+"Program Stoped"); break;
               case "1": printCash(); break;
               case "2": buyProduct(); break;
               case "3": buyStock(); break;
               case "4": setExpired(); break;
               case "5": printInDisplay("I1111111"); break;
               case "6": doSale(); break;
               case "7": printSales(); break;
               case "8": totalSales(); break;
               case "9": deleteFromInventory(); break;
               case "10": createProduct(); break;
               case "printp","PRINTP","PrintP": printProductos(); break;
               case "printi","PRINTI","PrintI": printInventory(); break;
               case "printc","PRINTC","PrintC": printClientes(); break;
               case "load","LOAD","Load":loadInventory(); break;
               case "p","P": chetitos(); break;
               default : System.out.println(RED+"Input Error"); break;
           }
       }while(!op.equals("0"));
    }
    public static void chetitos(){
        double cash = CASH.getValue();
        double newCash = cash += 10000;
        CASH.setValue(newCash);
        System.out.println(GREEN+"+10000$"+RESET);
        printDisplayCash();
    }
    public static void menu(){
        System.out.println("");
        System.out.println("== "+RED+"SHOP MANAGER"+RESET+" ==");
        System.out.println("- 0)Exit");
        System.out.println("- 1)Print Cash");
        System.out.println("- 2)Buy Product");
        System.out.println("- 3)Buy Stock");
        System.out.println("- 4)Set Expired");
        System.out.println("- 5)Print Invenory");
        System.out.println("- 6)Sell Product");
        System.out.println("- 7)Print Sales");
        System.out.println("- 8)Total Sales");
        System.out.println("- 9)Delete from Inventory");
        System.out.println("- 10)Create Product");
        // System.out.println("- 11)Change Currency");
        System.out.println("");
        System.out.print("Option: ");
    }
    public static void printCash(){
        System.out.println("");
       System.out.println("== CASH ==");
       System.out.println("- "+GREEN+CASH.toString()+RESET);
    }
    public static void printDisplayCash(){
        System.out.println("CASH: "+GREEN+CASH.toString()+RESET);
    }
    public static void printClientes(){
        System.out.println("== "+YELLOW+"CLIENT LIST"+RESET+" ==");
        for(Client c : clientes){
            clientToString(c);
        }
    }
    public static void printInventory(){
        if(isInventoryEmpty()){return;}
        System.out.println("Shop Inventory...");
        for(Product i : inventory){
            System.out.println();
            System.out.println("== "+BLUE+i.getName()+RESET+" ==");
            System.out.println("- ID: "+i.getID());
            System.out.println("- Name: "+i.getName());
            System.out.println("- Sell Price: "+i.getSellerPrice().toString());
            if(!i.sellerPrice.getCurrency().equals("$")){
                System.out.println("- Sell Price In Dollars: "+amountToStringInDollar(i.getSellerPrice()));
            }
            System.out.println("- Shop Price: "+i.getShopPrice().toString());
            if(!i.shopPrice.getCurrency().equals("$")){
                System.out.println("- Shop Price In Dollars: "+amountToStringInDollar(i.getShopPrice()));
            }
            System.out.println("- Stock: "+i.getStock());
            System.out.println("- Expired: "+i.getExpired());
        }
    }
    public static void loadInventory(){
        if(isLoaded){System.out.println(RED+"Cant Load More Products"+RESET); return;}
        isLoaded = true;
        System.out.println("Loading Products...");
        for(int i=0; i<5; i++){
            Product p = productos.get(i);
            p.setStock(10);
            inventory.add(p);
        }
    }
    public static void printProductos(){
        for(Product p : productos){
            System.out.println();
            System.out.println("== "+PURPLE+p.getName()+RESET+" ==");
            System.out.println("- ID: "+p.getID());
            System.out.println("- Name: "+p.getName());
            System.out.println("- Sell Price: "+p.getSellerPrice().toString());
            if(!p.sellerPrice.getCurrency().equals("$")){
                System.out.println("- Sell Price In Dollars: "+amountToStringInDollar(p.getSellerPrice()));
            }
            System.out.println("- Shop Price: "+p.getShopPrice().toString());
            if(!p.shopPrice.getCurrency().equals("$")){
                System.out.println("- Shop Price In Dollars: "+amountToStringInDollar(p.getShopPrice()));
            }
            System.out.println("- TotalProducts: "+p.getTotalProducts());
        }
    }
    public static void totalSales(){
        if(isSalesEmpty()){return;}
        System.out.println("Total Sales: "+CYAN+sales.size()+RESET+" sales");
    }
    public static void printSales(){
        if(isSalesEmpty()){return;}
        System.out.println("Loading Sales...");
        for(Sale s : sales){
            saleToString(s);
        }
    }
    public static void createProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("== "+RED+"PRODUCT CREATOR"+RESET+" ==");
        lastPIDManager();
        System.out.print("Product Name? ");
        String name = scanner.nextLine();
        Product isp = searchInProductsReturnP(name);
        if(isp!= null){System.out.println("Product with name "+RED+name+RESET+" alrady exists"); return;}
        System.out.print("Price for "+RED+name+RESET+"? ");
        String price = scanner.nextLine();
        if(!isNumber(price)){return;}
        int priceNum = stringToInt(price);
        System.out.println("Currency for "+RED+name+RESET+"?");
        System.out.println("1)"+RED+"Dollar"+RESET+" $");
        System.out.println("2)"+RED+"Euro"+RESET+" E");
        System.out.println("3)"+RED+"Ruble"+RESET+" P");
        System.out.println("4)"+RED+"Yen"+RESET+" Y");
        System.out.print("Option: ");
        String currency = scanner.nextLine();
        if(isDollarString(currency)){currency = "$";} 
        else if(isEuroString(currency)){currency = "E";} 
        else if(isRubleString(currency)){currency = "R";} 
        else if(isYenString(currency)){currency = "Y";} 
        else {currency = "$";}
        Amount sellPrice = new Amount(priceNum,currency);
        Amount shopPrice = new Amount(priceNum*2,currency);
        System.out.print("Total Productos for "+RED+name+RESET+"? ");
        String tp = scanner.nextLine();
        if(!isNumber(tp)){return;}
        int tpNum = stringToInt(tp);
        productos.add(new Product(lastPID,name,sellPrice,shopPrice,0,tpNum,false));
        System.out.println(GREEN+name+" created sucessfully"+RESET);
        
    }
    public static void doSale(){
        if(isInventoryEmpty()){return;}
        Scanner scanner = new Scanner(System.in);
        System.out.print("Whitch Client is Buying? ");
        String cl = scanner.nextLine();
        Client c = searchInClientsReturnC(cl);
        if(c == null){
            System.out.println(RED+"Client not registered"+RESET);
            System.out.print("Want to Register "+YELLOW+cl+RESET+" as new Client? [true/false] ");
            String op = scanner.nextLine();
            if(!isStringTrue(op)){System.out.println(RED+"Operation Cancelled"+RESET); return;}
            lastCIDManager();
            Amount ca = randomAmount();
            System.out.println("New ClientID for "+YELLOW+cl+RESET+": "+lastCID);
            c = new Client(cl,lastCID,ca);
            clientes.add(c);
        }else{System.out.println("Welcome back "+YELLOW+c.getName()+RESET);}
        String toSearch;
        ArrayList<Integer> quantity = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();
        Product p;
        Amount toPay = new Amount(0,"$");
        double toPayAmount = 0;
        do{
           printInDisplay("I1101101");
           System.out.println(YELLOW+c.getName()+RESET+" total balance: "+GREEN+c.getBalance().toString()+RESET);
           System.out.print("Whitch Product is "+YELLOW+c.getName()+RESET+" Buying? ");
           toSearch = scanner.nextLine();
           if(toSearch.equals("0")||toSearch.equals("Exit")){break;}
           p = searchInInventroyReturnP(toSearch);
           if(p!=null){
                if(hasStockDisponible(p,1)){
                    boolean hasPInCart = false;
                    for(int i=0; i<products.size(); i++){
                         if(products.get(i) == p){
                            quantity.set(i,quantity.get(i)+1);
                            hasPInCart = true;
                            break;
                         }
                    }
                    if(!hasPInCart){
                        products.add(p);
                        quantity.add(1); 
                    }
                    toPay = convertToDollar(p.getShopPrice());
                    toPayAmount += round2Decimals(toPay.getValue());
                    toPay.setValue(round2Decimals(toPayAmount));
                    System.out.println("To Pay: "+RED+toPayAmount+"$"+RESET);
                }
            }
        }
        while(!toSearch.equals("0")||toSearch.equals("Exit"));
        if(!c.canClientPay(toPay)){return;}

        lastSIDManager();
        sales.add(new Sale(lastSID,c,products,quantity,toPay));
        int num = 0;
        for(Product pr : products){
            System.out.println(RED+"-"+quantity.get(num)+" "+pr.getName()+RESET);
            p = searchInInventroyReturnP(pr.getID());
            p.setStock(p.getStock()-quantity.get(num));
            num++;
        }
        System.out.println(GREEN+"+"+convertToDollar(toPay).toString()+RESET);
        double actualCash = CASH.getValue();
        actualCash += convertToDollar(toPay).getValue();
        CASH.setValue(actualCash);
        printDisplayCash();
    }
    public static void buyStock(){
        if(isInventoryEmpty()){return;}
        Scanner scanner = new Scanner(System.in);
        printInDisplay("I1110111");
        printDisplayCash();
        System.out.println("Witch Product want to buy? ");
        Product p = searchProductInInventoryReturnP();
        if(p == null){System.out.println(RED+"Product not Found"+RESET);return;}
        System.out.print("How manny "+BLUE+p.getName()+RESET+" want to buy? ");
        String n = scanner.nextLine();
        if(!isNumber(n)){return;}
        int nd = stringToInt(n);
        if(!hasProductDisponible(p,nd)){return;}
        double cost = convertToDollar(p.getSellerPrice()).getValue();
        double tc = cost * nd;
        System.out.println("Transaction Cost: "+RED+tc+"$"+RESET);
        printDisplayCash();
        System.out.print("Continue?[true/false] ");
        String op = scanner.nextLine();
        if(CASH.getValue()<tc){
            System.out.println(RED+"Transaction Cancelled, Need more CASH"+RESET);
            return;
        }
        if(!isStringTrue(op)){System.out.println(RED+"Transaction Cancelled"+RESET); return;}
        System.out.println("Succesfully buyed "+nd+" "+p.getName());
        double newCash = CASH.getValue() - tc;
        CASH.setValue(newCash);
        System.out.println("- "+RED+tc+"$"+RESET);
        System.out.println("+ "+GREEN+nd+" "+RESET+p.getName());
        if(hasProductInventory(p)){
             p.setStock(p.getStock()+nd);
        }
        else{
            p.setStock(nd);
            inventory.add(p);
        }
        p.setTotalProducts(p.getTotalProducts()-nd);
        printDisplayCash();
    }
    public static void buyProduct(){
        Scanner scanner = new Scanner(System.in);
        printInDisplay("P1110110");
        printDisplayCash();
        System.out.println("Witch Product want to buy? ");
        Product p = searchProductInProductsReturnP();
        if(p == null){System.out.println(RED+"Product not Found"+RESET);return;}
        System.out.print("How manny "+PURPLE+p.getName()+RESET+" want to buy? ");
        String n = scanner.nextLine();
        if(!isNumber(n)){return;}
        int nd = stringToInt(n);
        if(!hasProductDisponible(p,nd)){return;}
        double cost = convertToDollar(p.getSellerPrice()).getValue();
        double tc = cost * nd;
        System.out.println("Transaction Cost: "+RED+tc+"$"+RESET);
        printDisplayCash();
        System.out.print("Continue?[true/false] ");
        String op = scanner.nextLine();
        if(CASH.getValue()<tc){
            System.out.println(RED+"Transaction Cancelled, Need more CASH"+RESET);
            return;
        }
        if(!isStringTrue(op)){System.out.println(RED+"Transaction Cancelled"+RESET); return;}
        System.out.println("Succesfully buyed "+nd+" "+PURPLE+p.getName()+RESET);
        double newCash = CASH.getValue() - tc;
        CASH.setValue(newCash);
        System.out.println(RED+"-"+tc+"$"+RESET);
        System.out.println(GREEN+"+"+nd+p.getName()+RESET);
        if(hasProductInventory(p)){
            p.setStock(p.getStock()+nd);
        }
        else{
            p.setStock(nd);
            inventory.add(p);
        }
        p.setTotalProducts(p.getTotalProducts()-nd);
        printDisplayCash();
    }
    public static void setExpired(){
        if(isInventoryEmpty()){return;}
        Scanner scanner = new Scanner(System.in);
        printInDisplay("I1100001");
        Product p = searchProductInInventoryReturnP();
        if(p == null){System.out.println(RED+"Product not Found"+RESET);return;}
        System.out.print("Is "+BLUE+p.getName()+RESET+" Product Expired? [true/false] ");
        String op = scanner.nextLine();
        if(isStringTrue(op)){
            setExpiredPrice(p); 
            p.setExpired(true);
        }
        else{
            p.setExpired(false);
        }
        String result = invertedBooleanPainter(p.getExpired());
        System.out.println(BLUE+p.getName()+RESET+" set Expired to "+result); 
    }
    public static void deleteFromInventory(){
        if(isInventoryEmpty()){return;}
        Scanner scanner = new Scanner(System.in);
        printInDisplay("I1100100");
        Product p = searchProductInInventoryReturnP();
        if(p == null){System.out.println(RED+"Product not Found"+RESET);return;}
        System.out.print("Want to delete all "+BLUE+p.getName()+RESET+" form Shop Inventory? [true/false] ");
        String op = scanner.nextLine();
        if(!isStringTrue(op)){System.out.println(RED+"Operation Cancelled"+RESET); return;}
        System.out.println("All "+BLUE+p.getName()+RESET+" deleted from Shop Inventory");
        System.out.println(RED+"-"+p.getStock()+p.getName()+RESET);
        inventory.remove(p);
    }
    public static Product searchProductInInventoryReturnP(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("== "+BLUE+"INVENTORY SEARCHER"+RESET+" ==");
        System.out.print("Product to Search? ");
        String toSearch = scanner.nextLine();
        return searchInInventroyReturnP(toSearch);
    }
    public static Product searchProductInProductsReturnP(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("== "+PURPLE+"PRODUCT SEARCHER"+RESET+" ==");
        System.out.print("Product to Search? ");
        String toSearch = scanner.nextLine();
        return searchInProductsReturnP(toSearch);     
    }
    public static Client searchClientInClientsReturnC(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("== "+YELLOW+"CLIENT SEARCHER"+RESET+" ==");
        System.out.print("Client to Search? ");
        String toSearch = scanner.nextLine();
        return searchInClientsReturnC(toSearch);     
    }
} 
