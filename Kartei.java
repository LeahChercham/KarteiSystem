
/*
Die Klasse Kartei verwaltet die gesamten Freunde
Version 1.0
Author: Leah Chercham
*/
import java.io.*;
import java.util.Scanner;
import java.util.UUID;
import java.util.Vector;

public class Kartei {
    private static int bestand = 0;

    // Referenzvariable refFreund von Typ Freund anlegen und
    // mit null initialisieren

    // Eingabe rufen

    private static void auswahlAnzeigen() {
        System.out.println("Bitte Zahl und dann RETURN eingeben:");
        System.out.println("<1> Freund anlegen");
        System.out.println("<2> Freund suchen");
        System.out.println("<3> Freund veraendern");
        System.out.println("<4> Freund loeschen");
        System.out.println("<5> Anzahl gespeicherter Freunde angeben");
        System.out.println("<6> Beenden");
        auswahlAuswerten();
    }

    private static void auswahlAuswerten() {
        Scanner input = new Scanner(System.in); // Never closed ?
        int auswahl = input.nextInt();
        input.nextLine(); // Zeilenumbruch einlesen
        // input.close();

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
            // Hier Sachen speichern weil hier der einzige Ort zum Beenden des Programms
            // sein soll. Alle anderen rufen auswahl anzeigen wieder auf
            // das sollte in einer anderen Klasse sein zum Beispiel Verwaltung
            break;
        }
        default:
            System.out.println("Falsche Eingabe");
        }
    }

    // Das heisst ich muss Kartei konstruierin in Main
    public Kartei (String dateiName) throws Exception{
        File file = new File(dateiName);
        if(file.exists()){
            FileInputStream fis = new FileInputStream(dateiName);
            Scanner scan = new Scanner (fis);
            scan.useDelimiter("\\s*:\\s*");

            while(scan.hasNext()){
                scan.next();
                arr.add(new Freund(scan.next(), scan.next(), scan.next(), scan.nextInt(), scan.nextInt(), scan.next(), scan.next()));
            }
            fis.close();
        }
    }

    public void datenSpeichern(String dateiName) throws Exception{
        PrintStream out = new PrintStream(new FileOutputStream (dateiName));
        for(Freund element : arr)
        out.println(element + " :");
        out.close();
    }

    public static void main(String[] args) throws Exception{
        // Hier Kartei kreeieren ? Wie ruft man methoden von der command line java ?
        if(args.length == 1){
            Kartei kartei = new Kartei(args[0]);
            auswahlAnzeigen();
            kartei.datenSpeichern(args[0]);
        } else {
            System.out.println("Aufruf mit: Java Kartei Dateiname.txt");
        }

    }

    private static Vector<Freund> arr = new Vector<Freund>();

    private static void bestandAbfragen() {
        System.out.println("Du hast " + bestand + " Freunde in deiner Kartei.");
        auswahlAnzeigen();
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

        String schluessel = UUID.randomUUID().toString();

        arr.add(new Freund(vorname, nachname, geburtstag, telefon, handy, adresse, schluessel));
        bestand++;
        System.out.println("Der Freund " + vorname + " " + nachname + " wurde in der Kartei angelegt.");
        auswahlAnzeigen();
    }
    /*
     * Jetzt habe ich den Vector Freund in dieser Klasse. Eigentlich sollte er in
     * der Freund Klasse sein. Ich könnte zum Beispiel die Funktion freund anlegen
     * nach Freund.java verschieben. Oder ich erstelle eine dritte Klasse zur
     * verwaltung
     */

    // Eingabe speichern new Freund eingabe

    //

}