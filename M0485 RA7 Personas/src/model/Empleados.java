package model;
public class Empleados extends Personas{
    // VARIABLES
    public Personas persona;
    public String ID;
    public double salario; 
    // CONSTRUCTOR
    public Empleados(Personas persona,String ID,double salario){ // Muy Ilegal
        super(persona.nombre,persona.sexo,persona.edad,persona.DNI);
        this.ID = ID;
        this.salario = salario;
    }
    public Empleados(String nombre,String sexo,int edad,String DNI,String ID, double salario){
        super(nombre,sexo,edad,DNI);
        this.ID = ID;
        this.salario = salario;
    }
    // GETTER
   public String getID(){
       return ID;
   }
   public double getSalario(){
       return salario;
   }
   // SETTER
   public void setID(String ID){
       this.ID = ID;
   }
   public void setSalario(double salario){
       this.salario = salario;
   }
   
}
