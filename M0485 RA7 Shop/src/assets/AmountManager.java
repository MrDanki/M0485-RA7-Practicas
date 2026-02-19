package assets;
// IMPORTS
import java.util.Random;
// CLASES
import model.Amount;
import static main.Main.*;
import static assets.LogicManager.*;
// MAIN
public class AmountManager{
    // GLOBAL VARIABLES
    public static String shopCurrency = "$";
    // MAIN
    public static void amountLoader(){
        divisas.add(new Amount(1.0,"$"));
        divisas.add(new Amount(0.85,"E"));
        divisas.add(new Amount(75,"P")); 
        divisas.add(new Amount(155,"Y"));
    }
    public static Amount convertToDollar(Amount moneda){
        double value = moneda.getValue();
        String currency = moneda.getCurrency();
        switch(currency){
            case "$": value /= 1; break;
            case "E": value /= 0.85; break;
            case "P": value /= 75; break;
            case "Y": value /= 155; break;
        }
        Amount result = new Amount(round2Decimals(value),"$");
        return result;
    }
    public static String amountToStringInDollar(Amount value){
        Amount v = convertToDollar(value);
        return v.toString();
    }
    public static Amount randomAmount(){
        Random random = new Random();
        Amount result = new Amount(0,"");
        int currency = random.nextInt(1,5);
        double value = random.nextInt(5000,10000);
        switch(currency){
            case 1: 
                value *= 1; 
                result.setValue(round2Decimals(value));
                result.setCurrency("$");
                break;
            case 2: 
                value *= 0.85; 
                result.setValue(round2Decimals(value));
                result.setCurrency("E");
                break;
            case 3: 
                value *= 75; 
                result.setValue(round2Decimals(value));
                result.setCurrency("R");
                break;
            case 4: 
                value *= 155; 
                result.setValue(round2Decimals(value));
                result.setCurrency("Y");
                break;
        }
        return result;
    }
    public static boolean isDollarString(String input){
        String[]possibles ={"1","$","Dollar","DOLLAR","dollar",};
        for(String p : possibles){
            if(p.equals(input)){return true;}
        }
        return false; 
    }
    public static boolean isEuroString(String input){
        String[]possibles ={"2","E","Euro","EURO","euro",};
        for(String p : possibles){
            if(p.equals(input)){return true;}
        }
        return false; 
    }
    public static boolean isRubleString(String input){
        String[]possibles ={"3","P","Ruble","RUBLE","ruble",};
        for(String p : possibles){
            if(p.equals(input)){return true;}
        }
        return false; 
    }
    public static boolean isYenString(String input){
        String[]possibles ={"4","Y","Yen","YEN","yen",};
        for(String p : possibles){
            if(p.equals(input)){return true;}
        }
        return false; 
    }
}
