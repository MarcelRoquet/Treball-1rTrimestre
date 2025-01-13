
import java.util.Scanner;
public class Main {
    // crear registre de comptes per saber tamany de la llista
    int registres=0;
    // variables per crar el compte
    static String compte;
    static String nom, cognom, compcontrasenya, contrasenya, username;
    static double saldoActual, mensualidad;
    static  String [][] matriz = new String[0][6]; // creem una matriu  aqui per que quedin totes les dades guardades
    //variables per verificar el compte
    static Double[][] estalvi = new Double[0][3];
    static int index;
    private Scanner input =new Scanner(System.in);
    public  static void main(String[] args) {
        Main Banc = new Main();          //creacio instancia
        do {
            Banc.teCompte();
            switch (compte){
                case "R":
                    Banc.crearCompte();
                    break;
                case "I":
                    if (!Banc.verificarCredencials()){
                        break;
                    }
                    Banc.mostrarBenvinguda();
                    Banc.mostrarMenu();
                    break;
            }
        } while (!compte.equals("O"));

    }
    public String teCompte() {
        do {                            //bucle hasta que nos de una respuesta posible
            System.out.println("Vols inicia sessió (I) o registrarte (R) o sortir (O)");
            compte = input.nextLine().toUpperCase();      //leemos input y lo pasamos a maysuculas
        } while (!compte.equals("R") && !compte.equals("I") && !compte.equals("O"));
        if (compte.equals("O")){
            System.out.println("Sortint...");
        }
        return compte;
    }

    public void crearCompte() {
        // Crear variables necesaries

        registres++;  //augmntem els registres per fer que la matriu s'agrandi cada cop que algui es registri

        String[][] nuevaMatriz = new String[registres][6];   // creem una segona matriu que sera una fila mes gran a la anterior per registrar una persona mes
        Double[][] nouEstalvi = new Double[registres][3];
        for (int i = 0; i < registres - 1; i++) {
            for (int j = 0; j < 6; j++) {
                nuevaMatriz[i][j] = matriz[i][j];  // aqui copiem la informacio de la matriz anterior a la nova per operar amb aquesta sense perdre informacio
            }
            for (int j = 0; j < 3; j++) {
                nouEstalvi[i][j] = estalvi[i][j];
            }

        }
        matriz = nuevaMatriz;
        estalvi = nouEstalvi;
// preguntem les dades que s'introduirant a la matriu
        System.out.print("Ingresa el teu nom: ");
        nom = input.nextLine();

        System.out.print("Ingresa el teu cognom: ");
        cognom = input.nextLine();

        // verificar que el username posat no existeix
         verificarUsername();


        do {
            System.out.print("Ingresa una contrasenya: ");
            contrasenya = input.nextLine();
            System.out.print("Verifica  la contrasnya: ");
            compcontrasenya = input.nextLine();
            if (!contrasenya.equals(compcontrasenya)){
                System.out.println("Les contrasenyes no coincideixen");
            }
        } while (!contrasenya.equals(compcontrasenya)); // Verifica que l'usuari ha ficat la contrasenya que volia i no s'ha equivocat en una lletra o numero


        saldoActual = llegirDouble("Ingresa el teu  Saldo Actual: ");

        mensualidad = llegirDouble("Ingresa la teva  Mensualitat: ");


        //introduim les dades donades a la matriu
        matriz[registres - 1][0] = nom;
        matriz[registres - 1][1] = cognom;
        matriz[registres - 1][2] = contrasenya;
        matriz[registres - 1][3] = username;
        matriz[registres - 1][4] = Double.toString(saldoActual); //serveix per psar els doubles a strings
        matriz[registres - 1][5] = Double.toString(mensualidad);

        System.out.println("Dades guardades correctament");
    }
public void verificarUsername(){
    boolean b;
    do {
        b=true;

        System.out.print("Ingresi un Username: ");
        username = input.nextLine();
        for (int i = 0; i < registres-1; i++) {
            if (username.equals(matriz[i][3])) {
                System.out.println("Username no dispobible");
                b = false;   // si el username es repeteix convertim el booleano en falso

                break;
            }
        }
    } while (!b);

}
    public boolean verificarCredencials(){
        index=-1;  //donem valor inicial al index
        System.out.print("Introdueix el teu username: ");
        username=input.nextLine();
        System.out.print("Introdueix la contrasenya: ");
        compcontrasenya=input.nextLine();
//bucle per verificar el usuari i la contraseña, amb aixo es podran repetir noms d'usuari
        for (int i = 0; i < registres; i++) {

            if (username.equals(matriz[i][3])) {

                if (compcontrasenya.equals(matriz[i][2])) {
                    index = i;  // si coincideix la contraseña amb el username de la mateixa fila li dodem un valor al index
                    break;
                }
            }
        }

        if (index != -1) {  //segons el index trobat trobem una solucio
            System.out.println("Usuari i contrasenya acceptades ");
            return true;
        } else {
            System.out.println("Usuari o contrasenya incorrectes.");
            return false;

        }

    }
    public static double llegirDouble(String missatge) {
        Scanner llegir = new Scanner(System.in);

        double x = 0;
        boolean valorCorrecte = false;

        do {
            System.out.print(missatge);
            valorCorrecte = llegir.hasNextDouble();

            if (!valorCorrecte) {
                System.out.println("ERROR: Valor incoherent.");
                llegir.nextLine();
            } else {
                x = llegir.nextDouble();
                llegir.nextLine();
            }

        } while (!valorCorrecte);

        return x;
    }

    public void mostrarBenvinguda(){
        System.out.println(" Benvingut/da "+ matriz[index][0]);
    }

    public  void mostrarMenu() {
        int opcioMenu = 0;

        do {
            opcioMenu = llegirEnter("Les opcions del menú són:" +
                    "\n1- Dades personals" +
                    "\n2- Ingresar diners" +
                    "\n3- Retirar diners" +
                    "\n4- Planificació " +
                    "\n5- Modificar dades personals" +
                    "\n6- Tanca sessió", 1, 6);


            switch (opcioMenu) {
                case 1:
                    dadesPersonals();
                    break;
                case 2:
                    ingressarDiners();
                    break;
                case 3:
                    retirarDiners();
                    break;
                case 4:
                    planificamentDiners();
                    break;
                case 5:
                    modificarDades();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opció no valida ");
                    break;
            }

        } while (opcioMenu != 6);
    }

    private static int llegirEnter(String missatge, int min, int max) {
        Scanner llegir = new Scanner(System.in);

        int x = 0;
        boolean valorCorrecte = false;

        do {
            System.out.println(missatge);
            valorCorrecte = llegir.hasNextInt();

            if (!valorCorrecte) {
                System.out.println("ERROR: Valor no enter.");
                llegir.nextLine();
            } else {                      // Tinc un enter
                x = llegir.nextInt();
                llegir.nextLine();
                if (x < min || x > max) {
                    System.out.println("Opció no valida");
                    valorCorrecte = false;
                }
            }
        } while (!valorCorrecte);

        return x;
    }

    public void dadesPersonals(){
        System.out.print("Nom: ");
        System.out.println(matriz[index][0]);
        System.out.print("Cognom: ");
        System.out.println(matriz[index][1]);
        System.out.print("Username: ");
        System.out.println(matriz[index][3]);
        System.out.print("Saldo actual: ");
        System.out.println(matriz[index][4]);
        System.out.print("Mensualitat: ");
        System.out.println(matriz[index][5]);
    }

    public void ingressarDiners(){
        System.out.println("Quina quanititat vols ingresar? ");
        System.out.print("Diners: ");

        double Diners = input.nextDouble();

        double saldoActual = Double.parseDouble(matriz[index][4]);

        saldoActual += Diners;

        matriz[index][4] = Double.toString(saldoActual);

        System.out.println("Acceptat, ara el teu saldo es de: "+matriz[index][4]);
    }

    public void retirarDiners(){
        System.out.println("Quina quanititat vols retirar? ");
        System.out.print("Diners: ");
        double Diners;
        double saldoActual = Double.parseDouble(matriz[index][4]);
        do {
             Diners = input.nextDouble();
        } while (Diners>saldoActual);

            saldoActual -= Diners;

        matriz[index][4] = Double.toString(saldoActual);

        System.out.println("Acceptat, ara el teu saldo es de: "+matriz[index][4]);

    }

    public void planificamentDiners(){

        //Inici VARIABLES
        int teclat =0;
        boolean sortir =false;
        boolean dinersEstalviar=false;
        boolean metaFeta =false;
        boolean plaFet = false;
        double metaEstalvi =0;
        double tantEstalviar = 0;
        double estalviPerMes=0.0;
        Double [][] nouEstalvi = new Double[registres][3];
        //Final VARIABLES

        do{
            System.out.println("------------------");
            //MENU
        teclat = llegirEnter("Les opcions del menú són:" +
                "\n1- Establir una nova meta d'estalvi" +
                "\n2- Pla d'estalviament" +
                "\n3- Mostrar pla d'estalviament" +
                "\n4- Tornar enrere", 1, 4);
            System.out.println("------------------");


            switch (teclat) {
                case 1:
                    metaEstalvi = llegirDouble("Diners a estalviar:");
                    estalvi[index][0]=metaEstalvi;  //Llegir la variable i guardarla a la matriu estalvi/registres
                    dinersEstalviar=true;
                    System.out.println("Has configurat una meta d'estalvi de " + metaEstalvi);
                    break;
                case 2:
                    if (estalvi[index][0]==null) { //Si no has establit la meta no pots accedir al cas 2
                        System.out.println("Primer ingresa els diners que vols estalviar");
                    } else {
                        if (metaEstalvi < 1000) {
                            gestionarEstalvi("És una meta modesta.");
                        } else if (metaEstalvi < 5000 && metaEstalvi > 1000) {
                            gestionarEstalvi("És una meta ambiciosa.");
                        } else if (metaEstalvi > 5000) {
                            gestionarEstalvi("És una meta gran!");
                        }
                        plaFet=true;
                    }
                    break;
                case 3:
                    if (!plaFet) { //Si no has fet el pla d'estalvi no pots accedir al cas 3
                        System.out.println("Primer necessites establir un pla d'estalviament");
                    } else {
                        System.out.println("------------------");
                        System.out.println("La teva meta d'estalvi es: " + estalvi[index][0]);
                        System.out.println("Estalviaras per mes: " + estalvi[index][1]);
                        System.out.println("------------------");
                    }
                case 4:
                    System.out.println("Tornant enrere...");
                    sortir = true;
                    break;
                    default: System.out.println("Opció no vàlida.");
            }
        }while(!sortir);
    }
    public static double calcularEstalviMensual (double tantEstalviar){
        return (mensualidad*estalvi[index][1])/estalvi[index][0];
    }
    public boolean demanarGuardar(){
        boolean metaFeta=false;
        System.out.println("Vols guardar aquest pla d'estalvi? (S/N)");
        String guardar = input.next();
        if (guardar.equalsIgnoreCase("s")) {
            System.out.println("Guardant....");
            System.out.println("El teu pla d'estalvi ha estat guardat correctament");
            metaFeta=true;
            return true;
        } else{
            System.out.println("El pla d'estalvi no s'ha guardat");
            return false;
        }
    }
    private void gestionarEstalvi(String missatge) {    //Metode per gestionar el % i fer el calcul
        boolean metaFeta;
        System.out.println(missatge);
        System.out.println("La teva meta d'estalvi es de: " + estalvi[index][0]);
        System.out.println("Quin tant % del teu sou vols estalviar:");
        estalvi[index][1] = input.nextDouble();
        estalvi[index][1] = calcularEstalviMensual(estalvi[index][0]); //Ens envia a calcularEstalviMensual
        System.out.println("Estalviaràs " + estalvi[index][1] + " per mes");
        metaFeta = demanarGuardar(); //Demana guardar el pla per el metode "demanarGuardar"
    }

    public void modificarDades(){
        int seleccio=0;

        do{
            seleccio = llegirEnter("Quina dada desitja modificar?" +
                    "\n1- Nom" +
                    "\n2- Cognom" +
                    "\n3- Username" +
                    "\n4- Mensualitat" +
                    "\n5- Tornar Enrere", 1, 5);
            switch (seleccio){
                case 1:
                    modificarNom();
                    break;
                case 2:
                    modificarCognom();
                    break;
                case 3:
                    modificarUsername();
                case 4:
                    modificarMensualitat();
            }
        }while(seleccio!=5);
    }
    public void modificarNom(){
        System.out.println("------------------");
        System.out.println("Nom actual: " + matriz[registres - 1][0]);
        System.out.println("Introdueix el nou Nom:");
        String nouNom = input.next();
        matriz[registres - 1][0] = nouNom;
        System.out.println("------------------");
        System.out.println("Confirma el teu nou Nom:");
        String confirmaNom = input.next();
        if (confirmaNom.equals(nouNom)){
            System.out.println("Nom cambiat correctament a: "+ nouNom);
            System.out.println("------------------");
        } else {
            System.out.println("El nom no coincideix");
            System.out.println("Redirigint al menu...");
            return;
        }
        System.out.println("Desitja cambiar tambe el seu cognom? (S/N)");
        String anaraCognom = input.next();
        if (anaraCognom.equalsIgnoreCase("s")) {
            modificarCognom();
        } else{
            System.out.println("Redirigint al menu...");
        }
    }
    public void modificarCognom(){
        System.out.println("------------------");
        System.out.println("Cognom actual: " + matriz[registres - 1][1]);
        System.out.println("Introdueix el nou Cognom:");
        String nouCognom = input.next();
        matriz[registres - 1][1] = nouCognom;
        System.out.println("------------------");
        System.out.println("Confirma el teu nou Cognom:");
        String confirmaCognom = input.next();
        if (confirmaCognom.equals(nouCognom)){
            System.out.println("Cognom cambiat correctament a: "+ nouCognom);
            System.out.println("------------------");
        } else {
            System.out.println("El cognom no coincideix");
            System.out.println("Redirigint al menu...");
            return;
        }
    }
    public void modificarUsername(){
        System.out.println("------------------");
        System.out.println("Username actual: " + matriz[registres - 1][3]);
        System.out.println("Introdueix el nou Username:");
        String nouUsername = input.next();
        matriz[registres - 1][3] = nouUsername;
        System.out.println("------------------");
        System.out.println("Confirma el teu nou Username:");
        String confirmaUsername = input.next();
        if (confirmaUsername.equals(nouUsername)){
            System.out.println("Username cambiat correctament a: "+ nouUsername);
            System.out.println("------------------");
        } else {
            System.out.println("El nou username no coincideix");
            System.out.println("Redirigint al menu...");
        }
    }
    public void modificarMensualitat(){
        System.out.println("------------------");
        System.out.println("Mensualitat actual: " + matriz[registres - 1][5]);
        System.out.println("Introdueix la nova Mensualitat:");
        String novaMensualitat = input.next();
        matriz[registres - 1][5]=novaMensualitat;
        System.out.println("Mensualitat cambiada correctament");
        System.out.println("------------------");

    }

}


