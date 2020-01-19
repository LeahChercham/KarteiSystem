import java.util.Scanner;

public class TestEingabe {
    public static void main(String[] args) {
        Scanner eingabe = new Scanner(System.in);

        System.out.print("Vorname eingeben: ");
        String vorname = eingabe.next();

        System.out.print("Nachname eingeben: ");
        String nachname = eingabe.next();

        System.out.print("Sie heissen " + vorname + " " + nachname);

        eingabe.close();
    }
}