package model;
import main.Logable;
import static main.Main.*;
import static assets.ColorManager.*;
public class Employee extends Person implements Logable{
    // VARIABLES
    public String ID;
    public String password;
    // CONSTRUCTOR
    public Employee(String name,String ID,String password){
        super(name);
        this.ID = ID;
        this.password = password;
    }
    // GETTER
    public String getID(){
        return ID;
    }
    public String getPassword(){
        return password;
    }
    // SETTER
    public void setID(String ID){
        this.ID = ID;
    }
    public void setPassword(String password){
        this.password = password;
    }
    // LOGIN CORRECT
    @Override
    public boolean isLoginCorrect(){
        for(Employee e : empleados){
            if(e.getID().equals(ID) && e.getPassword().equals(password)){
                System.out.println(GREEN+"Login Correct"+RESET);
                System.out.println("Welcome to the Shop Manager "+RED+name+RESET);
                return true;
            }
        }
        System.out.println(RED+"Login Incorrect"+RESET); 
        return false;
    }
}
