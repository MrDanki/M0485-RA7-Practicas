package assets;
// IMPORTS
// CLASES
import static assets.ColorManager.*;
// MAIN
public class LogicManager {
    public static boolean isNumber(String num){
        try{
            Double.parseDouble(num);
            return true;
        }
        catch(NumberFormatException e){
            System.out.println(RED+"Input Error"+RESET);
            return false;
        } 
    }
    public static int stringToInt(String input){
        return Integer.parseInt(input);
    }
    public static double round2Decimals(double input){
        return Math.floor(input*100)/100;
    }
    public static boolean isStringTrue(String input){
        String[]possibles ={"True","true","TRUE","Yes","yes","YES","T","t"};
        for(String p : possibles){
            if(p.equals(input)){return true;}
        }
        return false; 
    }
}
