
import java.util.Scanner;
public class Main {
    // crear registre de comptes per saber tamany de la llista
    int registres=0;
    static String compte;
    private Scanner input =new Scanner(System.in);
    public  static void main(String[] args) {
        Main Banc = new Main();          //creacio instancia
        do {

            if (Banc.teCompte()) {     // comprueba si tiene cuenta o no y gracias el metodo teCompte y dependiendo del resultado hace una cosa u otra

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

        String nom, cognom, compcontrasenya, contrasenya, username;
        double saldoActual, mensualidad;
        registres++;  //augmntem els registres per fer que la matriu s'agrandi cada cop que algui es registri

        String [][] matriz = new String[registres][6];

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
        return false;
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
