package controller;
import java.util.Random;
import main.Main;
import static main.Main.DNIs;
import static main.Main.clientes;
import static main.Main.empleados;
import static main.Main.matriculas;
import static main.Main.personas;
import model.Clientes;
import model.Empleados;
import model.Personas;
import model.Vehiculos;

public class Assets {
    // ID GENERATOR
    public static void IDGenerator(){
        Main.lastEID = String.format("E%03d",empleados.size());
        Main.lastCID = String.format("C%03d",clientes.size());
        
    }
    // BUSCADORES
    public static Empleados searchByEmpleadoIDReturnE(String input){
        for(Empleados e : empleados){
            if(e.getID().equals(input)){
                return e;
            }
        }
        return null;
    }
    public static boolean searchByEmpleadoID(String input){
        for(Empleados e : empleados){
            if(e.getID().equals(input)){
                return true;
            }
        }
        return false;
    }
    public static Clientes searchByClienteIDReturnC(String input){
        for(Clientes c : clientes){
            if(c.getID().equals(input)){
                return c;
            }
        }
        return null;
    }
    public static boolean searchByClienteID(String input){
        for(Clientes c : clientes){
            if(c.getID().equals(input)){
                return true;
            }
        }
        return false;
    }
    public static Personas searchByDNIReturnP(String input){
        for(Personas p : personas){
            if(p.getDNI().equals(input)){
                return p;
            }
        }
        return null;
    }
    public static boolean searchByDNI(String input){
        for(Personas p : personas){
            if(p.getDNI().equals(input)){
                return true;
            }
        }
        return false;
    }
    public static Personas searchByNameReturnP(String input){
        for(Personas p : personas){
            if(p.getNombre().equals(input)){
                return p;
            }
        }
        return null;
    }
    public static boolean searchByName(String input){
        for(Personas p : personas){
            if(p.getNombre().equals(input)){
                return true;
            }
        }
        return false;
    }
    // MATRICULA GENERATOR 1111AAA
    public static String MatriculaGenerator(){
        Random random = new Random();
        String[] letras = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        int num = 4;
        int let = 3;
        String lastMatricula = "";
        for(int i=0; i<num;i++){
            int indice = random.nextInt(1,9);
            lastMatricula = lastMatricula + String.valueOf(indice);
        }
        for(int i=0; i<let;i++){
            int indice = random.nextInt(letras.length);
            lastMatricula = lastMatricula + letras[indice];
        }
        // Check Same Matricula
        for(String m : matriculas){
            if(m.equals(lastMatricula)){
                MatriculaGenerator();
                break;
            }
        }
        
        matriculas.add(lastMatricula);
        return lastMatricula;
    }
    // DNI GENERATOR 11111111A
    public static String DNIGenerator(){
        Random random = new Random();
        String[] letras = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        int num = 8;
        int let = 1;
        String lastDNI = "";
        for(int i=0; i<num;i++){
            int indice = random.nextInt(1,9);
            lastDNI = lastDNI + String.valueOf(indice);
        }
        for(int i=0; i<let;i++){
            int indice = random.nextInt(letras.length);
            lastDNI = lastDNI + letras[indice];
        }
        // Check Same DNI
        for(String d : DNIs){
            if(d.equals(lastDNI)){
                DNIGenerator();
                break;
            }
        }
        DNIs.add(lastDNI);
        return lastDNI;
    } 
}
