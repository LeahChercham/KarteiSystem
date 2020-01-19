
/*
Die Klasse Kartei verwaltet die gesamten Freunde
Version 1.0
Author: Leah Chercham
*/
import java.util.Scanner;

public class Kartei {
    private static int bestand = 0;

    // Referenzvariable refFreund von Typ Freund anlegen und
    // mit null initialisieren

    // Eingabe rufen
    public static void main(String[] args) {
        // Hier Kartei kreeieren ? Wie ruft man methoden von der command line java ? 
    }

    public void bestandAbfragen() {
        System.out.println("Du hast " + bestand + " Freunde in deiner Kartei.");
    }

    public void freundAnlegen() {
        Freund refFreund = null;
        Scanner eingabe = new Scanner(System.in);

        System.out.print("Vorname eingeben: ");
        String vorname = eingabe.next();

        System.out.print("Nachname eingeben: ");
        String nachname = eingabe.next();
        System.out.print("Geburtstag eingeben: ");
        String geburtstag = eingabe.next();
        System.out.print("Handy Nummer eingeben: ");
        int handy = eingabe.nextInt();
        System.out.print("Telefon Nummer eingeben: ");
        int telefon = eingabe.nextInt();
        System.out.print("Adresse eingeben: ");
        String adresse = eingabe.next();
        System.out.print("Schluessel eingeben: ");
        String schluessel = eingabe.next();

        refFreund = new Freund(vorname, nachname, geburtstag, telefon, handy, adresse, schluessel);
        bestand++;
        System.out.println("Der Freund " + vorname + " " + nachname + " wurde in der Kartei angelegt.");
        eingabe.close();
    }

    // Eingabe speichern new Freund eingabe

    //

}