package model;
public class Vehiculos extends Personas {
    // VARIABLES
    public Personas persona;
    public String matricula;
    // CONSTRUCTOR
    public Vehiculos(Personas persona,String matricula){ // Muy Ilegal
        super(persona.nombre,persona.sexo,persona.edad,persona.DNI);
        this.matricula = matricula;
    }
    public Vehiculos(String nombre,String sexo,int edad,String DNI,String matricula){
        super(nombre,sexo,edad,DNI);
        this.matricula = matricula;
    }
    // GETTER
    public String getMatricula(){
        return matricula;
    }
    // SETTER
    public void setMatricula(String matricula){
       this.matricula = matricula;
    }
}
