package model;
public abstract class Person {
    // VARIABLES
    public String name;
    // CONSTRUCTOR
    public Person(String name){
        this.name = name;
    }
    // GETTER
    public String getName(){
        return name;
    }
    // SETTER
    public void setName(String name){
        this.name = name;
    }
}
