package assets;
import model.Product;
import model.Sale;
import java.util.ArrayList;
import static main.Main.*;
import static assets.ColorManager.*;
public class SaleManager{
    public static String lastSID = "S000";
    public static void saleToString(Sale s){
        ArrayList<Integer> quantity = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();
        for(Integer sq : s.getQuantity()){
            quantity.add(sq);
        }
        for(Product sp : s.getProducts()){
            products.add(sp);
        }
        System.out.println("");
        System.out.println("== "+CYAN+s.getID()+RESET+" ==");
        System.out.println("- ClientID: "+CYAN+s.getClient().getID()+RESET);
        System.out.println("- Client Name: "+CYAN+s.getClient().getName()+RESET);
        System.out.println("- PRODUCT LIST: ");
        for(int i=0; i<quantity.size(); i++){
            String ID = "ID: "+CYAN+products.get(i).getID()+RESET;
            String name = "Name: "+CYAN+products.get(i).getName()+RESET;
            String num = "Quantity: "+CYAN+quantity.get(i)+RESET;
            System.out.println(" - "+ID+" / "+name+" / "+num);
        }
        System.out.println("- Amount: "+CYAN+s.getAmount().toString()+RESET);
    }
    public static boolean isSalesEmpty(){
        if(sales.isEmpty()){
            System.out.println(RED+"No Sales Disponible"+RESET);
            return true;
        }
        return false;
    }
    public static void lastSIDManager(){
        lastSID = String.format("S%03d",sales.size());
    }
}
