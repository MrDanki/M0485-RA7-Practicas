package model;
public class Clientes extends Personas{
    // VARIABLES
    public Personas persona;
    public String ID;
    public String date;
    public boolean vip;
    // CONSTRUCTOR
    public Clientes(Personas persona,String ID,String date,boolean vip){ // Muy Ilegal
        super(persona.nombre,persona.sexo,persona.edad,persona.DNI);
        this.ID = ID;
        this.date = date;
        this.vip = vip;
    }
    public Clientes(String nombre,String sexo,int edad,String DNI,String ID,String date,boolean vip){
        super(nombre,sexo,edad,DNI);
        this.ID = ID;
        this.date = date;
        this.vip = vip;
    }
    // GETTER
    public String getID(){
       return ID;
    }
    public String getDate(){
       return date;
    }
    public boolean getVip(){
       return vip;
    }
    // SETTER
    public void setID(String ID){
       this.ID = ID;
    }
    public void setDate(String date){
       this.date = date;
    }
    public void  setVip(boolean vip){
       this.vip = vip;
    }
}
