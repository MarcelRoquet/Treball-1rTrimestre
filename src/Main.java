
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
                    planificamentDiners();
                    break;
                case 5:
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
        int teclat =0;
        boolean sortir =false;
        boolean dinersEstalviar=false;
        int metaEstalvi =0;
        int tantEstalviar = 0;

        do{
        teclat = llegirEnter("Les opcions del menú són:" +
                "\n1- Establir una meta d'estalvi" +
                "\n2- Pla d'estalviament" +
                "\n3- Tanca sessió", 1, 3);


            switch (teclat) {
                case 1:
                    System.out.println("Quants diners vols estalviar?:");
                    System.out.println("Diners:");
                    metaEstalvi = input.nextInt();
                    dinersEstalviar=true;
                    System.out.println("Has configurat una meta d'estalvi de " + metaEstalvi);
                    break;
                case 2:
                    if (!dinersEstalviar) {
                        System.out.println("Primer ingresa els diners que vols estalviar");
                    } else {
                        if (metaEstalvi < 1000) {
                            System.out.println("La teva meta d'estalvi es de: "+ metaEstalvi);
                            System.out.println("Quin tant % del teu sou vols estalviar:");
                            tantEstalviar=input.nextInt();
                            double estalviPerMes = calcularEstalviMensual(tantEstalviar);
                            System.out.println("Estalviaràs " + estalviPerMes + " per mes");

                        break;
                        } else if (metaEstalvi < 5000 && metaEstalvi > 1000) {
                            System.out.println("La teva meta d'estalvi es de: "+ metaEstalvi);

                        } else if (metaEstalvi > 5000) {
                            System.out.println("La teva meta d'estalvi es de: "+ metaEstalvi);

                        }
                    }
                break;
            }
        }while(!sortir);

    }
    public static double calcularEstalviMensual (int tantEstalviar){
        return (mensualidad*tantEstalviar)/100.0;
    }
}
