
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

            if (Banc.teCompte()) {     // comprueba si tiene cuenta o no y gracias el metodo teCompte y dependiendo del resultado hace una cosa u otra
                Banc.verificarCredencials();
            } else {
                Banc.crearCompte();
            }
        } while (!compte.equals("O"));

    }
    public boolean teCompte() {

        do {                            //bucle hasta que nos de una respuesta posible
            System.out.println("Vols inicia sessió (I) o registrarte (R) o sortir (O)");
            compte = input.nextLine().toUpperCase();  //leemos input y lo pasamos a maysuculas
        } while (!compte.equals("R") && !compte.equals("I") && !compte.equals("O"));

        if (compte.equals("R")) {   // Si le da A Regitrarse enviaremos un False
            return false;
        } else {
            return true;
        }
    }


    public void crearCompte() {
        // Crear variables necesaries


        registres++;  //augmntem els registres per fer que la matriu s'agrandi cada cop que algui es registri

        String[][] nuevaMatriz = new String[registres][6];
        for (int i = 0; i < registres - 1; i++) {
            for (int j = 0; j < 6; j++) {
                nuevaMatriz[i][j] = matriz[i][j];
            }
        }
        matriz = nuevaMatriz;

        System.out.print("Ingresa el teu nom: ");
        nom = input.nextLine();

        System.out.print("Ingresa el teu cognom: ");
        cognom = input.nextLine();

        System.out.print("Ingresi un Username: ");
        username = input.nextLine();


        do {
            System.out.print("Ingresa una contrasenya: ");
            contrasenya = input.nextLine();
            System.out.print("Verifica  la contrasnya: ");
            compcontrasenya = input.nextLine();
            if (!contrasenya.equals(compcontrasenya)){
                System.out.println("Les contrasenyes no coincideixen");
            }
        } while (!contrasenya.equals(compcontrasenya)); // Verifica que l'usuari ha ficat la contrasenya que volia i no s'ha equivocat en una lletra o numero

        System.out.print("Ingresa el teu  Saldo Actual: ");
        saldoActual = input.nextDouble();

        System.out.print("Ingresa la teva  Mensualitat: ");
        mensualidad = input.nextDouble();
        input.nextLine();

        //introduim les dades donades a la matriu
        matriz[registres - 1][0] = nom;
        matriz[registres - 1][1] = cognom;
        matriz[registres - 1][2] = contrasenya;
        matriz[registres - 1][3] = username;
        matriz[registres - 1][4] = Double.toString(saldoActual); //serveix per psar els doubles a strings
        matriz[registres - 1][5] = Double.toString(mensualidad);

        System.out.println("Dades guardades correctament");
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
            System.out.println("Desintges registrarte ? (S/N)");
            return false;

        }



    }

    public void mostrarBenvinguda(){

    }

    public void mostrarMenu(){

    }

    public void dadesPersonals(){

    }

    public void ingressarDiners(){

    }

    public void retirarDiners(){

    }

    public void mostrarMenu2(){

    }
}
