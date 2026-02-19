package assets;
// IMPORTS
// CLASES
import model.Amount;
import model.Client;
import static assets.AmountManager.*;
import static assets.ColorManager.*;
import static main.Main.*;
// MAIN
public class ClientManager {
    public static String lastCID = "C000";
    public static void lastCIDManager(){
        lastCID = String.format("C%03d",clientes.size());
    }
    public static void clientLoader(){
        String[]names ={"Jeff","Kapplan","Gustav","Adolf","Pavel","Donald"};
        for(int c=0; c<names.length; c++){
            lastCIDManager();
            Amount ca = randomAmount();
            clientes.add(new Client(names[c],lastCID,ca));
        }
    }
    public static void clientToString(Client c){
        String ID = "ID: "+YELLOW+c.getID()+RESET;
        String name = "Name: "+YELLOW+c.getName()+RESET;
        String balance = "Balance: "+YELLOW+c.getBalance().toString()+RESET;
        System.out.println("- "+ID+" / "+name+" / "+balance);
    }
    public static Client searchInClientsReturnC(String input){
        for(Client c : clientes){
            if(input.equals(c.getID())){return c;}
            if(input.equals(c.getName())){return c;}
        }
        return null;
    }
}
