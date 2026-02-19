package assets;
// CLASSES
import model.Employee;
import static main.Main.*;
// MAIN
public class EmployeeManager{
    public static void employeeLoader(){
        empleados.add(new Employee("Testificate","123","test"));
        empleados.add(new Employee("MrDanki","E000","Z"));
    }
    public static String searchEmployeeNameWithID(String input){
        for(Employee e: empleados){
            if(e.getID().equals(input)){
                return e.getName();
            }
        }
        return null;
    }   
}
