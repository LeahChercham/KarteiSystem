
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

    private static void auswahlAnzeigen() {
        System.out.println("Bitte Zahl und dann RETURN eingeben:");
        System.out.println("<1> Freund anlegen");
        System.out.println("<2> Freund suchen");
        System.out.println("<3> Freund verändern");
        System.out.println("<4> Freund löschen");
        System.out.println("<5> Anzahl gespeicherter Freunde angeben");
        System.out.println("<6> Beenden");
        auswahlAuswerten();
    }

    private static void auswahlAuswerten() {
        Scanner eingabe = new Scanner(System.in);
        int auswahl = eingabe.nextInt();
        eingabe.nextLine(); // Zeilenumbruch einlesen
        eingabe.close();

        switch (auswahl) {
        case 1: {
            freundAnlegen();
            break;
        }
        case 2: {
            break;
        }
        case 3: {
            break;
        }
        case 4: {
            break;
        }
        case 5: {
            bestandAbfragen();
            break;
        }
        case 6: {
            break;
        }
        default:
            System.out.println("Falsche Eingabe");
        }
    }

    public static void main(String[] args) {
        // Hier Kartei kreeieren ? Wie ruft man methoden von der command line java ?
        auswahlAnzeigen();
    }

    private static void bestandAbfragen() {
        System.out.println("Du hast " + bestand + " Freunde in deiner Kartei.");
    }

    private static void freundAnlegen() {
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

        new Freund(vorname, nachname, geburtstag, telefon, handy, adresse, schluessel);
        bestand++;
        System.out.println("Der Freund " + vorname + " " + nachname + " wurde in der Kartei angelegt.");
        eingabe.close();
    }

    // Eingabe speichern new Freund eingabe

    //

}