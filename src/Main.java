
import java.util.Scanner;
public class Main {
    private Scanner input =new Scanner(System.in);
    public  static void main(String[] args) {
        Main Banc = new Main();          //creacio instancia
        if (Banc.teCompte()){     // comprueba si tiene cuenta o no y gracias el metodo teCompte y dependiendo del resultado hace una cosa u otra

        } else {

        }

    }
    public boolean teCompte() {
        String compte;
        do {                            //bucle hasta que nos de una respuesta posible
            System.out.println("Vols inicia sessió (I) o registrarte (R)");
            compte = input.nextLine().toUpperCase();  //leemos input y lo pasamos a maysuculas
        } while (compte.equals("R") || compte.equals("I"));

        if (compte.equals("R")) {   // Si le da A Regitrarse enviaremos un False
            return false;
        } else {
            return true;
        }
    }


    public void crearCompte() {

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
