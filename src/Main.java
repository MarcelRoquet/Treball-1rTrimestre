import java.util.Scanner;
public class Main {
    // crear registre de comptes per saber tamany de la llista
    int registres = 0;
    // variables per crar el compte
    static String compte;
    static String nom, cognom, compcontrasenya, contrasenya, username;
    static double saldoActual, mensualidad;
    static  String [][] matriz = new String[0][6]; // creem una matriu  aqui per que quedin totes les dades guardades
    //variables per verificar el compte
    static Double[][] estalvi = new Double[0][3];
    static int index;
    static Scanner input = new Scanner(System.in);

    public  static void main(String[] args) {
        Main Banc = new Main();          //creacio instancia

        do {
            Banc.teCompte();

            switch (compte){
                case "R":
                    Banc.crearCompte();
                    break;
                 case "I":
                    if (!Banc.verificarCredencials())
                        break;
                    else
                        Banc.mostrarMenu(" Benvingut/da "+ matriz[index][0]);

                    break;
            }
        } while (!compte.equals("O"));

    }
    public String teCompte() {
        do {

            //bucle hasta que nos de una respuesta posible
            System.out.println("Vols iniciar sessió (I) o registrarte (R) o sortir (O)");
            System.out.print("Opció: ");
            compte = input.nextLine().toUpperCase();      //leemos input y lo pasamos a maysuculas
        } while (!compte.equals("R") && !compte.equals("I") && !compte.equals("O"));

        if (compte.equals("O"))
            System.out.println("Sortint...");

        return compte;
    }

    public void crearCompte() {
        // Crear variables necesaries

        registres++;  //augmntem els registres per fer que la matriu s'agrandi cada cop que algui es registri

        String[][] nuevaMatriz = new String[registres][6];// creem una segona matriu que sera una fila mes gran a la anterior per registrar una persona mes
        Double[][] nouEstalvi = new Double[registres][3];

        for (int i = 0; i < registres - 1; i++) {
            // aqui copiem la informacio de la matriz anterior a la nova per operar amb aquesta sense perdre informacio
            System.arraycopy(matriz[i], 0, nuevaMatriz[i], 0, 6);

            System.arraycopy(estalvi[i], 0, nouEstalvi[i], 0, 3);

        }

        matriz = nuevaMatriz;
        estalvi = nouEstalvi;

        // preguntem les dades que s'introduirant a la matriu
        nom = llegirParaula("Ingresa el teu nom: ");
        cognom = llegirParaula("Ingresa el teu cognom: ");

        // verificar que el username posat no existeix
        verificarUsername();
        // preguntar contraseña
        password();
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
    public static String password() {
        Scanner input = new Scanner(System.in);
        do {
            do {
                System.out.print("Ingresa una contrasenya: ");
                contrasenya = input.nextLine();
                if (contrasenya.length() <= 5)
                    System.out.println("La contrasenya ha de tenir minim 5 caràcters");

                if (!contrasenya.matches(".*[A-Z].*"))
                        System.out.println("La contrasenya ha de tenir almenys una majúscula");

                if (!contrasenya.matches(".*[a-z].*"))
                        System.out.println("La contrasenya ha de tenir almenys una minuscula");

            } while (contrasenya.length() <= 5 || !contrasenya.matches(".*[A-Z].*") || !contrasenya.matches(".*[a-z].*") ) ;

            System.out.print("Verifica  la contrasnya: ");
            compcontrasenya = input.nextLine();

            if (!contrasenya.equals(compcontrasenya))
                System.out.println("Les contrasenyes no coincideixen");

        } while (!contrasenya.equals(compcontrasenya)); // Verifica que l'usuari ha ficat la contrasenya que volia i no s'ha equivocat en una lletra o numero

        return contrasenya;
    }
    public static String llegirParaula(String missatge) {
        Scanner input = new Scanner(System.in);
        boolean teNumero;
        String palabra;

        do {
            teNumero = false;
            System.out.print(missatge);
            palabra = input.nextLine();

            for (int i = 0; i < palabra.length(); i++) {
                if (Character.isDigit(palabra.charAt(i))) { // Verifica si es un numero
                    teNumero = true;
                    System.out.println("No pots intruduir una xifra ");
                    break;
                }
            }
        } while (teNumero);

        return palabra;
    }

public void verificarUsername(){
    boolean b;

    do {
        b = true;

        System.out.print("Ingresi un Username: ");
        username = input.nextLine();
        for (int i = 0; i < registres - 1; i++) {
            if (username.equals(matriz[i][3])) {
                System.out.println("Username no disponible.");
                b = false;   // si el username es repeteix convertim el booleano en falso

                break;
            }
        }
    } while (!b);

}
    public boolean verificarCredencials(){
        index = -1;  //donem valor inicial al index
        System.out.print("Introdueix el teu username: ");
        username = input.nextLine();
        System.out.print("Introdueix la contrasenya: ");
        compcontrasenya = input.nextLine();

        //bucle per verificar el usuari i la contraseña, amb aixo es podran repetir noms d'usuari (ya no)
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

        double x = 0;
        boolean valorCorrecte = false;

        do {
            System.out.print(missatge);
            valorCorrecte = input.hasNextDouble();

            if (!valorCorrecte) {
                System.out.println("ERROR: Valor incoherent.");
                input.nextLine();
            } else {
                x = input.nextDouble();
                input.nextLine();
            }

        } while (!valorCorrecte);

        return x;
    }
    public void mostrarMenu(String missatge) {
        int opcioMenu = 0;
        System.out.println(missatge);

        do {
            opcioMenu = llegirEnter("Les opcions del menú són principal :" +
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
        int modDades = 0;

        modDades= llegirEnter("Que vols modificar ? " +
                "\n1- Nom" +
                "\n2- Cognom" +
                "\n3- Username " +
                "\n4- Contrasenya  " +
                "\n5- Mensualitat "
                    , 1, 5);

        switch (modDades){
            case 1:
                modString("Escriu el teu nou nom: ", 0,"nom");
                break;
            case 2:
                modString("Escriu el teu nou cognom: ", 1, "cognom");
                break;
            case 3:
                modusername();
                break;
            case 4:
                modcontraseña();
                break;
            case 5:
                modmansualitat();
                break;
        }
    }

    private void modmansualitat() {
        double newMensualitat = 0;

        System.out.println("La teva mensualitat asctual es : "+  matriz[index][5]);
        System.out.print("Escriu la teva nova mensualitat : ");

        newMensualitat = input.nextDouble();
        matriz[index][5] = String.valueOf(newMensualitat);
        System.out.println("Dades guardades correctament, " + matriz[index][5]);
    }

    private void modcontraseña() {
        String newPassword;
        String continuar = "c";
        boolean acceptada = false;

        // bucle hasta que ponga bien la contraseña dee comprovacion o quiera salir
        do {
            System.out.print("Per modificar la contrasnya has de introduir-la primer: ");
            newPassword = input.nextLine();

            if (!newPassword.equals(matriz[index][2])){
                System.out.println("Contrasenya incorrecte");
                System.out.print("Presiona 'f' per sortir o qualsevol tecla per continuar: ");
                continuar = input.nextLine().toLowerCase();
            } else
             acceptada = true;
        } while (!newPassword.equals(matriz[index][2]) && !continuar.equals("f"));

        if (acceptada) {
            System.out.println("Contrasenya acceptada ");
            password();
            matriz[index][2]=contrasenya;
            System.out.println("Nova contrasenya desada");
        }
    }

    private void modusername() {
        String usernameActual;
        System.out.println("El teu username actual es: "+  matriz[index][3]);
        System.out.print("Escriu el teu nou username: ");

        usernameActual = input.nextLine();
        matriz[index][3] = usernameActual;
        System.out.println("Dades guardades correctament, "+ matriz[index][3]);
    }

    private void modString (String missatge, int index2, String noms) {
        String nouString;
        System.out.println("El tenu " +noms+ " actual es: " +  matriz[index][index2]);

        nouString = llegirParaula(missatge);
        matriz[index][index2] = nouString;
        System.out.println("Dades guardades correctament.");
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
        double Diners = llegirDouble("Quina quanititat vols  ingresar ? "+"\n Diners: ");
        double saldoActual = Double.parseDouble(matriz[index][4]);

        saldoActual += Diners;
        matriz[index][4] = Double.toString(saldoActual);

        System.out.println("Acceptat, ara el teu saldo es de: " + matriz[index][4]);
    }

    public void retirarDiners(){
        double saldoActual = Double.parseDouble(matriz[index][4]);
        double diners;
        do {
            diners = llegirDouble("Quina quanititat vols retirar? "+"\n Diners: ");

            if(diners>saldoActual)
                System.out.println("No pots retirar més diners del que tens");

        } while (diners > saldoActual);

        saldoActual -= diners;
        matriz[index][4] = Double.toString(saldoActual);

        System.out.println("Acceptat, ara el teu saldo es de: " + matriz[index][4]);
    }

    public void planificamentDiners(){
        int teclat = 0;
        boolean sortir =false;
        boolean dinersEstalviar=false;
        boolean metaFeta =false;
        double metaEstalvi =0;
        double tantEstalviar = 0;
        double estalviPerMes=0.0;
        Double [][] nouEstalvi = new Double[registres][3];


        do{
        teclat = llegirEnter("Les opcions del menú són:" +
                "\n1- Establir una nova meta d'estalvi" +
                "\n2- Pla d'estalviament" +
                "\n3- Mostrar pla d'estalviament" +
                "\n4- Tornar enrere", 1, 4);
            //

            switch (teclat) {
                case 1:
                    metaEstalvi = llegirDouble("Diners a estalviar:");
                    estalvi[index][0]=metaEstalvi;
                    dinersEstalviar=true;
                    System.out.println("Has configurat una meta d'estalvi de " + metaEstalvi);
                    break;
                case 2:
                    if (estalvi[index][0]==null) {
                        System.out.println("Primer ingresa els diners que vols estalviar");
                    } else {
                        if (metaEstalvi < 1000) {
                            System.out.println("La teva meta d'estalvi es de: "+ estalvi[index][0]);
                            System.out.print("Quin tant % del teu sou vols estalviar: ");

                            // corregir      estalvi[index][1]=tantEstalviar;
                            tantEstalviar=input.nextDouble();
                            estalviPerMes = calcularEstalviMensual(tantEstalviar);

                            estalvi[index][1]=   estalviPerMes;

                            System.out.println("Estalviaràs " + estalvi[index][1] + " per mes");
                            metaFeta=demanarGuardar();
                        } else if (metaEstalvi < 5000 && metaEstalvi > 1000) {


                        } else if (metaEstalvi > 5000) {


                        }

                    }
                    break;
                case 3:
                    if (!metaFeta) {
                        System.out.println("Primer necessites establir un pla d'estalviament");
                    } else {
                        System.out.println("------------------");
                        System.out.println("La teva meta d'estalvi es: " + estalvi[index][0]);
                        System.out.println("Estalviaras per mes: " + estalvi[index][1]);
                        System.out.println("");
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
        return (mensualidad*tantEstalviar)/100.0;
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
}
