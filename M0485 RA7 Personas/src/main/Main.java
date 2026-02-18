package main;
// CLASSES
import model.Clientes; // Personas - String ID - String Date - boolean vip
import model.Empleados; // Personas - String ID - Double salario
import model.Personas; // String nombre - String sexo - Int edad - String DNI
import model.Vehiculos; // Personas - String matricula
// METODOS
import java.util.Scanner;
import java.util.ArrayList;
import static controller.Assets.*;
// MAIN
public class Main {
    // ARRAYLIST GLOBALES
    public static ArrayList<Personas> personas = new ArrayList<>();
    public static ArrayList<Empleados> empleados = new ArrayList();
    public static ArrayList<Clientes> clientes = new ArrayList();
    public static ArrayList<Vehiculos> vehiculos = new ArrayList(); 
    public static ArrayList<String> DNIs = new ArrayList();
    public static ArrayList<String> matriculas = new ArrayList();
    // VARIABLES GLOBALES
    public static String lastCID = "C000"; // Clientes C000
    public static String lastEID = "E000"; // Empleados E000 
    private static boolean isLoaded = false;
    // MAIN CODE
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String op;
        do{
            menu();
            op = scanner.nextLine();
            switch(op){
                case "0": System.out.println("Programa Finalizado"); break;
                case "1": addPersona(); break;
                case "2": deletePersona(); break;
                case "3": modificarPersona(); break;
                case "4": buscarPersona();break;
                case "print","PRINT","Print": printAll();break;
                case "load","LOAD","Load":addPrefabs();break;
                default: System.out.println("Input Error"); break;
            }
        }while(!op.equals("0"));
    }
    public static void menu(){
        System.out.println("");
        System.out.println("= GESTOR DE PERSONAL =");
        System.out.println("0)Exit");
        System.out.println("1)Dar de Alta");
        System.out.println("2)Dar de Baja");
        System.out.println("3)Modificar");
        System.out.println("4)Buscar");
        System.out.println("");
        System.out.print("Opcion: ");
    }
    public static void addPrefabs(){
        if(isLoaded){
            System.out.println("LOAD no disponible");
            return;
        }
        isLoaded = true;
        System.out.println("Anadiendo 5 Clientes y 5 Empleados...");
        String[]nombresE={"Jeff","Robotnik","Hallan","John","Fingha"};
        String[]sexE={"H","H","H","H","H"};
        int[]edadE={35,57,15,8,16};
        double[]salarioE={2000,4000,1000,900,500};
        
        String[]nombresC={"Mika","Roberta","Kalinka","Katyusha","Zverobaya"};
        String[]sexC={"F","F","F","F","F"};
        int[]edadC={55,27,8,17,12};
        String[]dateC={"2019","1980","2004","1943","1942"};
        boolean[]vipC={false,false,false,true,true};
        
        for(int i=0; i<5; i++){
            Empleados newEmp = new Empleados(nombresE[i],sexE[i],edadE[i],DNIGenerator(),lastEID,salarioE[i]);
            empleados.add(newEmp);
            personas.add(newEmp);
            IDGenerator();
        }
        for(int i=0; i<5; i++){
            Clientes newCl = new Clientes(nombresC[i],sexC[i],edadC[i],DNIGenerator(),lastCID,dateC[i],vipC[i]);
            clientes.add(newCl);
            personas.add(newCl);
            IDGenerator();
        }
        System.out.println("");
        for(Empleados e : empleados){
            System.out.println("- "+e.getID()+" = "+e.getNombre());
        }
        System.out.println("");
        for(Clientes c : clientes){
            System.out.println("- "+c.getID()+" = "+c.getNombre());
        }
    }
    // PRINT
    public static void printAll(){
        if(personas.isEmpty()){
            System.out.println("Gestor de Personal Sin datos");
            return;
        }
        for(Personas p : personas){
            System.out.println("");
            System.out.println(" == "+p.getDNI()+" == ");
            System.out.println("- Nombre: "+p.getNombre());
            System.out.println("- Sexo: "+p.getSexo());
            System.out.println("- Edad: "+p.getEdad());
            System.out.println("- DNI: "+p.getDNI());
        }
        for(Empleados e : empleados){
            System.out.println("");
            System.out.println(" == "+e.getID()+" == ");
            System.out.println("- Nombre: "+e.getNombre());
            System.out.println("- Sexo: "+e.getSexo());
            System.out.println("- Edad: "+e.getEdad());
            System.out.println("- DNI: "+e.getDNI());
            System.out.println("- ID: "+e.getID());
            System.out.println("- Salario: "+e.getSalario());
        }
        for(Clientes c : clientes){
            System.out.println("");
            System.out.println(" == "+c.getID()+" == ");
            System.out.println("- Nombre: "+c.getNombre());
            System.out.println("- Sexo: "+c.getSexo());
            System.out.println("- Edad: "+c.getEdad());
            System.out.println("- DNI: "+c.getDNI());
            System.out.println("- ID: "+c.getID());
            System.out.println("- Date: "+c.getDate());
            System.out.println("- Vip: "+c.getVip());
        }
        for(Vehiculos v : vehiculos){
            System.out.println("");
            System.out.println(" == "+v.getMatricula()+" == ");
            System.out.println("- Nombre: "+v.getNombre());
            System.out.println("- Sexo: "+v.getSexo());
            System.out.println("- Edad: "+v.getEdad());
            System.out.println("- DNI: "+v.getDNI());
            System.out.println("- Matricula: "+v.getMatricula());
        }
    }
    // ADD
    public static void addPersona(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("== ANADIR USUARIO ==");
        System.out.println("Que tipo de usuario es?");
        System.out.println("1)Empleado");
        System.out.println("2)Cliente");
        System.out.print("Opcion: ");
        String op = scanner.nextLine();
        switch(op){
            case "1","EMPLEADO","Empleado","empleado","e","E": 
                System.out.print("Nombre del Usuario? ");
                String Ename = scanner.nextLine();
                System.out.print("Sexo del Usuario?[M/F] ");
                String Esex = scanner.nextLine();
                switch(Esex){
                    case "M","m","male","Male","MALE","hombre","Hombre","HOMBRE": Esex="M"; break;
                    case "F","f","female","Female","FEMALE","Mujer","mujer","MUJER": Esex="F"; break;
                    default: System.out.println("Input Error"); break;
                }
                System.out.print("Edad del Usuario? ");
                int Eage = scanner.nextInt(); scanner.nextLine();
                System.out.print("DNI del Usuario? ");
                String EDNI = scanner.nextLine();
                switch(EDNI){
                    case "Generator","GENERATOR","DNI","dni","Generate","GENERATE","GEN","gen": 
                        EDNI = DNIGenerator();
                        System.out.println("DNI GENERATOR = " + EDNI);
                        break;
                }
                System.out.print("Salario del Usuario? ");
                double salario = scanner.nextDouble();
                Empleados newEmp = new Empleados(Ename,Esex,Eage,EDNI,lastEID,salario);
                empleados.add(newEmp);
                personas.add(newEmp);
                IDGenerator();
                break;
            case "2","CLIENTE","Cliente","cliente","c","C": 
                System.out.print("Nombre del Usuario? ");
                String Cname = scanner.nextLine();
                System.out.print("Sexo del Usuario?[M/F] ");
                String Csex = scanner.nextLine();
                switch(Csex){
                    case "M","m","male","Male","MALE","hombre","Hombre","HOMBRE": Csex="M"; break;
                    case "F","f","female","Female","FEMALE","Mujer","mujer","MUJER": Csex="F"; break;
                }
                System.out.print("Edad del Usuario? ");
                int Cage = scanner.nextInt(); scanner.nextLine();
                System.out.print("DNI del Usuario? ");
                String CDNI = scanner.nextLine();
                switch(CDNI){
                    case "Generator","GENERATOR","DNI","dni","Generate","GENERATE","GEN","gen": 
                        CDNI = DNIGenerator();
                        System.out.println("DNI GENERATOR = " + CDNI);
                        break;
                }
                System.out.print("Fecha del Usuario? ");
                String date = scanner.nextLine();
                System.out.print("Es VIP el Usuario?[true/false] ");
                String isVip = scanner.nextLine();
                boolean vip = false;
                switch(isVip){
                    case "true","True","TRUE","SI","si","Si": vip = true; break;
                }
                
                Clientes newCl = new Clientes(Cname,Csex,Cage,CDNI,lastCID,date,vip);
                clientes.add(newCl);
                personas.add(newCl);
                IDGenerator();
                break;
                
            default: System.out.println("Input Error"); break;
        }
    }
    // DELETE
    public static void deletePersona(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("== ELIMINAR USUARIO ==");
        System.out.println("Por que dato quieres buscar?");
        System.out.println("1)DNI");
        System.out.println("2)Nombre");
        System.out.println("3)EmpleadoID");
        System.out.println("4)ClienteID");
        boolean exist = false;
        System.out.print("Opcion: ");
        String op = scanner.nextLine();
        switch(op){
            case "1","DNI","dni": System.out.print("Introduce el DNI a buscar: "); break;
            case "2","Nombre","nombre": System.out.print("Introduce el Nombre a buscar: "); break;
            case "3","EmpleadoID","empleadoid","EID": System.out.print("Introduce el EmpleadoID a buscar: "); break;
            case "4","ClienteID","clienteid","CID": System.out.print("Introduce el ClienteID a buscar: "); break;
            default: System.out.println("Input Error"); break;
        }
        String toSearch = scanner.nextLine();
        switch(op){
            case "1","DNI","dni": exist = searchByDNI(toSearch); break;
            case "2","Nombre","nombre": exist = searchByName(toSearch); break;
            case "3","EmpleadoID","empleadoid","EID": exist = searchByEmpleadoID(toSearch); break;
            case "4","ClienteID","clienteid","CID": exist = searchByClienteID(toSearch);break;
            default: System.out.println("Input Error"); break;
        }
        if(exist){
            switch(op){
                case "1","DNI","dni": 
                    System.out.println(toSearch+" si existe como DNI registrado");
                    for(Personas p : personas){
                        if(p.getDNI().equals(toSearch)){
                            System.out.println(toSearch+ " eliminado correctamente");
                            personas.remove(p);
                            break;
                        }
                    }
                    for(Empleados e : empleados){
                        if(e.getDNI().equals(toSearch)){
                            System.out.println("Empleado "+toSearch+" eliminado correctamente");
                            empleados.remove(e);
                            break;
                        }
                    }
                    for(Clientes c : clientes){
                        if(c.getDNI().equals(toSearch)){
                            System.out.println("Cliente "+toSearch+" eliminado correctamente");
                            clientes.remove(c);
                            break;
                        }
                    }
                    break;
                case "2","Nombre","nombre": 
                    System.out.println(toSearch+" si existe como Nombre registrado");
                    for(Personas p : personas){
                        if(p.getNombre().equals(toSearch)){
                            System.out.println("Persona "+toSearch+" eliminado correctamente");
                            personas.remove(p);
                            break;
                        }
                    }
                    for(Empleados e : empleados){
                        if(e.getNombre().equals(toSearch)){
                            System.out.println("Empleado "+toSearch+" eliminado correctamente");
                            empleados.remove(e);
                            break;
                        }
                    }
                    for(Clientes c : clientes){
                        if(c.getNombre().equals(toSearch)){
                            System.out.println("Cliente "+toSearch+" eliminado correctamente");
                            clientes.remove(c);
                            break;
                        }
                    }
                    break;
                case "3","EmpleadoID","empleadoid","EID": 
                    System.out.println(toSearch+" si existe como EmpleadoID registrado");
                    String EDNI = "";
                    for(Empleados e : empleados){
                        if(e.getID().equals(toSearch)){
                            System.out.println("Empleado "+toSearch+" eliminado correctamente");
                            EDNI = e.getDNI();
                            empleados.remove(e);
                            break;
                        }
                    }
                    for(Personas p : personas){
                        if(p.getDNI().equals(EDNI)){
                            System.out.println("Persona "+toSearch+" eliminado correctamente");
                            personas.remove(p);
                            break;
                        }
                    }
                    break;
                case "4","ClienteID","clienteid","CID": 
                    System.out.println(toSearch+" si existe como ClienteID registrado");
                    String CDNI = "";
                    for(Clientes c : clientes){
                        if(c.getID().equals(toSearch)){
                            System.out.println("Cliente "+toSearch+ " eliminado correctamente");
                            CDNI = c.getDNI();
                            clientes.remove(c);
                            break;
                        }
                    }
                    for(Personas p : personas){
                        if(p.getDNI().equals(CDNI)){
                            System.out.println("Persona "+toSearch+" eliminado correctamente");
                            personas.remove(p);
                            break;
                        }
                    }
                    break;
            } 
        }
        else{
            switch(op){
                case "1","DNI","dni": System.out.println(toSearch+" no existe como DNI registrado"); break;
                case "2","Nombre","nombre": System.out.println(toSearch+" no existe como Nombre registrado"); break;
                case "3","EmpleadoID","empleadoid","EID": System.out.println(toSearch+" no existe como EmpleadoID registrado"); break;
                case "4","ClienteID","clienteid","CID": System.out.println(toSearch+" no existe como ClienteID registrado");break;
            }
        }
    }
    // MODICIAR
    public static void modificarPersona(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("== MODIFICAR DATOS DE USUARIO ==");
        System.out.println("Por que dato quieres buscar?");
        System.out.println("1)DNI");
        System.out.println("2)Nombre");
        System.out.println("3)EmpleadoID");
        System.out.println("4)ClienteID");
        boolean exist = false;
        System.out.print("Opcion: ");
        String op = scanner.nextLine();
        switch(op){
            case "1","DNI","dni": System.out.print("Introduce el DNI a buscar: "); break;
            case "2","Nombre","nombre": System.out.print("Introduce el Nombre a buscar: "); break;
            case "3","EmpleadoID","empleadoid","EID": System.out.print("Introduce el EmpleadoID a buscar: "); break;
            case "4","ClienteID","clienteid","CID": System.out.print("Introduce el ClienteID a buscar: "); break;
            default: System.out.println("Input Error"); break;
        }
        String toSearch = scanner.nextLine();
        Personas target = null;
        switch(op){
            case "1","DNI","dni": 
                exist = searchByDNI(toSearch);
                target = searchByDNIReturnP(toSearch); 
                break;
            case "2","Nombre","nombre": 
                exist = searchByName(toSearch); 
                target = searchByNameReturnP(toSearch); 
                break;
            case "3","EmpleadoID","empleadoid","EID":
                exist = searchByEmpleadoID(toSearch);
                target = searchByEmpleadoIDReturnE(toSearch); 
                break;
            case "4","ClienteID","clienteid","CID": 
                exist = searchByClienteID(toSearch);
                target = searchByClienteIDReturnC(toSearch); 
                break;
            default: System.out.println("Input Error"); break;
        }
        if(exist){
            String toChange;
            switch(op){
                case "1","DNI","dni": 
                    System.out.println(toSearch+" si existe como DNI registrado");
                    changeMenu();
                    toChange = scanner.nextLine();
                    switch(toChange){
                        case "1","Nombre","NOMBRE","nombre","Name","NAME","name":
                            System.out.print("Nuevo Nombre? ");
                            String newName = scanner.nextLine();
                            target.setNombre(newName); 
                            break;
                        case "2","Sexo","SEXO","sexo","Genero","GENERO","genero":
                            System.out.print("Nuevo Sexo? ");
                            String newSex = scanner.nextLine();
                            switch(newSex){
                                case "M","m","male","Male","MALE","hombre","Hombre","HOMBRE": newSex="M"; break;
                                case "F","f","female","Female","FEMALE","Mujer","mujer","MUJER": newSex="F"; break;
                            }
                            target.setSexo(newSex); 
                            break;
                        case "3","Edad","EDAD","edad","Age","AGE","age":
                            System.out.print("Nueva Edad? ");
                            int newAge = scanner.nextInt();
                            target.setEdad(newAge); 
                            break;
                        case "4","DNI","dni","Dni":
                            System.out.print("Nuevo DNI? ");
                            String newDNI = scanner.nextLine();
                            switch(newDNI){
                                case "case","Generator","GENERATOR","Generate","GENERATE","GEN","gen":
                                    newDNI = DNIGenerator();
                                    System.out.println("Nuevo DNI: " + newDNI);
                            }
                            target.setDNI(newDNI); 
                            break;
                    }
                    break;
                case "2","Nombre","nombre": 
                    System.out.println(toSearch+" si existe como Nombre registrado");
                    changeMenu();
                    toChange = scanner.nextLine();
                    switch(toChange){
                        case "1","Nombre","NOMBRE","nombre","Name","NAME","name":
                            System.out.print("Nuevo Nombre? ");
                            String newName = scanner.nextLine();
                            target.setNombre(newName); 
                            break;
                        case "2","Sexo","SEXO","sexo","Genero","GENERO","genero":
                            System.out.print("Nuevo Sexo? ");
                            String newSex = scanner.nextLine();
                            switch(newSex){
                                case "M","m","male","Male","MALE","hombre","Hombre","HOMBRE": newSex="M"; break;
                                case "F","f","female","Female","FEMALE","Mujer","mujer","MUJER": newSex="F"; break;
                            }
                            target.setSexo(newSex); 
                            break;
                        case "3","Edad","EDAD","edad","Age","AGE","age":
                            System.out.print("Nueva Edad? ");
                            int newAge = scanner.nextInt();
                            target.setEdad(newAge); 
                            break;
                        case "4","DNI","dni","Dni":
                            System.out.print("Nuevo DNI? ");
                            String newDNI = scanner.nextLine();
                            switch(newDNI){
                                case "case","Generator","GENERATOR","Generate","GENERATE","GEN","gen":
                                    newDNI = DNIGenerator();
                                    System.out.println("Nuevo DNI: " + newDNI);
                            }
                            target.setDNI(newDNI); 
                            break;
                    }
                    break;
                case "3","EmpleadoID","empleadoid","EID":
                    System.out.println(toSearch+" si existe como Nombre registrado");
                    changeMenu();
                    toChange = scanner.nextLine();
                    switch(toChange){
                        case "1","Nombre","NOMBRE","nombre","Name","NAME","name":
                            System.out.print("Nuevo Nombre? ");
                            String newName = scanner.nextLine();
                            target.setNombre(newName); 
                            break;
                        case "2","Sexo","SEXO","sexo","Genero","GENERO","genero":
                            System.out.print("Nuevo Sexo? ");
                            String newSex = scanner.nextLine();
                            switch(newSex){
                                case "M","m","male","Male","MALE","hombre","Hombre","HOMBRE": newSex="M"; break;
                                case "F","f","female","Female","FEMALE","Mujer","mujer","MUJER": newSex="F"; break;
                            }
                            target.setSexo(newSex); 
                            break;
                        case "3","Edad","EDAD","edad","Age","AGE","age":
                            System.out.print("Nueva Edad? ");
                            int newAge = scanner.nextInt();
                            target.setEdad(newAge); 
                            break;
                        case "4","DNI","dni","Dni":
                            System.out.print("Nuevo DNI? ");
                            String newDNI = scanner.nextLine();
                            switch(newDNI){
                                case "case","Generator","GENERATOR","Generate","GENERATE","GEN","gen":
                                    newDNI = DNIGenerator();
                                    System.out.println("Nuevo DNI: " + newDNI);
                            }
                            target.setDNI(newDNI); 
                            break;
                    }
                    break;
                case "4","ClienteID","clienteid","CID":
                    System.out.println(toSearch+" si existe como Nombre registrado");
                    changeMenu();
                    toChange = scanner.nextLine();
                    switch(toChange){
                        case "1","Nombre","NOMBRE","nombre","Name","NAME","name":
                            System.out.print("Nuevo Nombre? ");
                            String newName = scanner.nextLine();
                            target.setNombre(newName); 
                            break;
                        case "2","Sexo","SEXO","sexo","Genero","GENERO","genero":
                            System.out.print("Nuevo Sexo? ");
                            String newSex = scanner.nextLine();
                            switch(newSex){
                                case "M","m","male","Male","MALE","hombre","Hombre","HOMBRE": newSex="M"; break;
                                case "F","f","female","Female","FEMALE","Mujer","mujer","MUJER": newSex="F"; break;
                            }
                            target.setSexo(newSex); 
                            break;
                        case "3","Edad","EDAD","edad","Age","AGE","age":
                            System.out.print("Nueva Edad? ");
                            int newAge = scanner.nextInt();
                            target.setEdad(newAge); 
                            break;
                        case "4","DNI","dni","Dni":
                            System.out.print("Nuevo DNI? ");
                            String newDNI = scanner.nextLine();
                            switch(newDNI){
                                case "case","Generator","GENERATOR","Generate","GENERATE","GEN","gen":
                                    newDNI = DNIGenerator();
                                    System.out.println("Nuevo DNI: " + newDNI);
                            }
                            target.setDNI(newDNI); 
                            break;
                    }
                    break;
            } 
        }
        else{
            switch(op){
                case "1","DNI","dni": System.out.println(toSearch+" no existe como DNI registrado"); break;
                case "2","Nombre","nombre": System.out.println(toSearch+" no existe como Nombre registrado"); break;
                case "3","EmpleadoID","empleadoid","EID": System.out.println(toSearch+" no existe como EmpleadoID registrado"); break;
                case "4","ClienteID","clienteid","CID": System.out.println(toSearch+" no existe como ClienteID registrado");break;
            }
        }
    }
    public static void changeMenu(){
        System.out.println("Que dato quieres modificar?");
        System.out.println("1)Nombre");
        System.out.println("2)Sexo");
        System.out.println("3)Edad");
        System.out.println("4)DNI");
        System.out.print("Opcion: ");
    }
    // BUSCAR PERSONA
    public static void buscarPersona(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("== BUSCAR USUARIO ==");
        System.out.println("Por que dato quieres buscar?");
        System.out.println("1)DNI");
        System.out.println("2)Nombre");
        System.out.println("3)EmpleadoID");
        System.out.println("4)ClienteID");
        boolean exist = false;
        System.out.print("Opcion: ");
        String op = scanner.nextLine();
        switch(op){
            case "1","DNI","dni": System.out.print("Introduce el DNI a buscar: "); break;
            case "2","Nombre","nombre": System.out.print("Introduce el Nombre a buscar: "); break;
            case "3","EmpleadoID","empleadoid","EID": System.out.print("Introduce el EmpleadoID a buscar: "); break;
            case "4","ClienteID","clienteid","CID": System.out.print("Introduce el ClienteID a buscar: "); break;
            default: System.out.println("Input Error"); break;
        }
        String toSearch = scanner.nextLine();
        switch(op){
            case "1","DNI","dni": exist = searchByDNI(toSearch); break;
            case "2","Nombre","nombre": exist = searchByName(toSearch); break;
            case "3","EmpleadoID","empleadoid","EID": exist = searchByEmpleadoID(toSearch); break;
            case "4","ClienteID","clienteid","CID": exist = searchByClienteID(toSearch);break;
            default: System.out.println("Input Error"); break;
        }
        if(exist){
            switch(op){
                case "1","DNI","dni": 
                    System.out.println(toSearch+" si existe como DNI registrado");
                    for(Personas p : personas){
                        if(p.getDNI().equals(toSearch)){
                            System.out.println("");
                            System.out.println(" == "+toSearch+" == ");
                            System.out.println("- Nombre: "+p.getNombre());
                            System.out.println("- Sexo: "+p.getSexo());
                            System.out.println("- Edad: "+p.getEdad());
                            System.out.println("- DNI: "+p.getDNI());
                        }
                    }
                    break;
                case "2","Nombre","nombre": 
                    System.out.println(toSearch+" si existe como Nombre registrado");
                    for(Personas p : personas){
                        if(p.getNombre().equals(toSearch)){
                            System.out.println("");
                            System.out.println(" == "+toSearch+" == ");
                            System.out.println("- Nombre: "+p.getNombre());
                            System.out.println("- Sexo: "+p.getSexo());
                            System.out.println("- Edad: "+p.getEdad());
                            System.out.println("- DNI: "+p.getDNI());
                        }
                    }
                    break;
                case "3","EmpleadoID","empleadoid","EID": 
                    System.out.println(toSearch+" si existe como EmpleadoID registrado"); 
                    for(Empleados e : empleados){
                        if(e.getID().equals(toSearch)){
                            System.out.println("");
                            System.out.println(" == "+toSearch+" == ");
                            System.out.println("- Nombre: "+e.getNombre());
                            System.out.println("- Sexo: "+e.getSexo());
                            System.out.println("- Edad: "+e.getEdad());
                            System.out.println("- DNI: "+e.getDNI());
                            System.out.println("- ID: "+e.getID());
                            System.out.println("- Salario: "+e.getSalario()+"$");
                        }
                    }
                    break;
                case "4","ClienteID","clienteid","CID": 
                    System.out.println(toSearch+" si existe como ClienteID registrado");
                    for(Clientes c : clientes){
                        if(c.getID().equals(toSearch)){
                            System.out.println("");
                            System.out.println(" == "+toSearch+" == ");
                            System.out.println("- Nombre: "+c.getNombre());
                            System.out.println("- Sexo: "+c.getSexo());
                            System.out.println("- Edad: "+c.getEdad());
                            System.out.println("- DNI: "+c.getDNI());
                            System.out.println("- ID: "+c.getID());
                            System.out.println("- Date: "+c.getDate());
                            System.out.println("- Vip: "+c.getVip());
                        }
                    }
                    break;
            } 
        }
        else{
            switch(op){
                case "1","DNI","dni": System.out.println(toSearch+" no existe como DNI registrado"); break;
                case "2","Nombre","nombre": System.out.println(toSearch+" no existe como Nombre registrado"); break;
                case "3","EmpleadoID","empleadoid","EID": System.out.println(toSearch+" no existe como EmpleadoID registrado"); break;
                case "4","ClienteID","clienteid","CID": System.out.println(toSearch+" no existe como ClienteID registrado");break;
            }
        }
        
    }
   
}
