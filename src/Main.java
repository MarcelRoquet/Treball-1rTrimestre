
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
        for (int i = 0; i < registres - 1; i++) {
            for (int j = 0; j < 6; j++) {
                nuevaMatriz[i][j] = matriz[i][j];  // aqui copiem la informacio de la matriz anterior a la nova per operar amb aquesta sense perdre informacio
            }
        }
        matriz = nuevaMatriz;
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
                    break;
                case 5:
                    modificardades();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opció no valida ");
                    break;
            }

        } while (opcioMenu != 6);
    }

    private void modificardades() {
        int moddades = 0;
        moddades= llegirEnter("Que vols modificar ? " +
                "\n1- Nom" +
                "\n2- Cognom" +
                "\n3- Username " +
                "\n4- Contrasenya  " +
                "\n5- Mensualitat "
                    , 1, 5);
        switch (moddades){
            case 1:
                modnom();
                break;
            case 2:
                modcognom();
                break;
            case 3:
                modusername();
                break;
            case 4:

            case 5:

        }
    }

    private void modusername() {
        String username_actual = null;
        System.out.println("El tenu cognom actual es: "+  matriz[index][2]);
        System.out.print("Escriu el teu nou cognom: ");
        username_actual=input.nextLine();
        matriz[index][2] = username_actual;
        System.out.println("Dades guardades correctament, "+ matriz[index][2]);
    }

    private void modcognom() {
        String cognom_actual = null;
        System.out.println("El tenu cognom actual es: "+  matriz[index][1]);
        System.out.print("Escriu el teu nou cognom: ");
        cognom_actual=input.nextLine();
        matriz[index][1] = cognom_actual;
        System.out.println("Dades guardades correctament, "+ matriz[index][1]);
    }

    private void modnom() {
        String nom_actual = null;
        System.out.println("El tenu nom actual es: "+  matriz[index][0]);
        System.out.print("Escriu el teu nou nom: ");
        nom_actual=input.nextLine();
        matriz[index][0] = nom_actual;
        System.out.println("Dades guardades correctament, "+ matriz[index][0]);
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
        double saldoActual = Double.parseDouble(matriz[index][4]);
        double Diners;
        do {
        System.out.println("Quina quanititat vols retirar? ");
        System.out.print("Diners: ");
             Diners = input.nextDouble();
        } while (Diners>saldoActual);

            saldoActual -= Diners;

        matriz[index][4] = Double.toString(saldoActual);

        System.out.println("Acceptat, ara el teu saldo es de: "+matriz[index][4]);

    }

    public void mostrarMenu2(){

    }
}
