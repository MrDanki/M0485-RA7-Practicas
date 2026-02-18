package model;
public abstract class Personas { // Abstract prohibe usar (new Personas)
    // VARIABLES
    public String nombre;
    public String sexo;
    public int edad;
    public String DNI;
    // CONSTRUCTOR
    public Personas(String nombre,String sexo,int edad,String DNI){
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
        this.DNI = DNI;
    }
    // GETTERS
    public String getNombre(){
        return nombre;
    }
    public String getSexo(){
        return sexo;
    }
    public int getEdad(){
        return edad;
    }
    public String getDNI(){
        return DNI;
    }
    // SETTER
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setSexo(String sexo){
        this.sexo = sexo;
    }
    public void setEdad(int edad){
        this.edad = edad;
    }
    public void setDNI(String DNI){
        this.DNI = DNI;
    }
}
