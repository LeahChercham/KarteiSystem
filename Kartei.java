
/*
Die Klasse Kartei verwaltet die gesamten Freunde
Version 1.0
Author: Leah Chercham
*/
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.Vector;

public class Kartei {
    private int bestand = 0;

    public Kartei(String dateiName) throws Exception {
        File file = new File(dateiName);
        if (file.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dateiName));

            System.out.println("ois: " + ois);
            System.out.println("ois.readobj: " + ois.readObject());

            // Scanner sur ois ?
            // Scanner scan = new Scanner(ois);
            // scan.useDelimiter("\\s:");

            // while (scan.hasNext()) {
            // Freund f = (Freund) ois.readObject();
            // System.out.println("scannext: " + f);
            // scan.next();
            // }
            // ois.close();
        }
    }

    // arrayList with initial capacity 100
    private ArrayList<Freund> friendsArray = new ArrayList<Freund>(100);
    // vecteur définis ici
    // private Vector<Freund> arr = new Vector<Freund>();

    // public void datenSpeichern(String dateiName) throws Exception {
    // // Serialization
    // ObjectOutputStream out = new ObjectOutputStream(new
    // FileOutputStream(dateiName));
    // System.out.println("Arr: " + arr);
    // for (Freund element : arr) {
    // out.writeObject(element);
    // }
    // out.close();
    // }

    private void freundSpeichern(Freund freund) throws Exception {
        // https://stackoverflow.com/questions/11661376/how-to-save-and-load-a-array-in-java
        // https://codereview.stackexchange.com/questions/98135/address-book-in-java
        // Use COLLECTIONS instead or Array List
        friendsArray.add(freund);
        // ObjectOutputStream out = new ObjectOutputStream(new
        // FileOutputStream("Freunde.ser", true));
        FileOutputStream fos = new FileOutputStream("friends.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(friendsArray);
        oos.flush();
        oos.close();
    }

    private void freundAnlegen() throws Exception {
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

        // C'est ici que je crée mon instance d'ami
        Freund f1 = new Freund(vorname, nachname, geburtstag, telefon, handy, adresse, schluessel);

        freundSpeichern(f1); // Array instead of friend

        bestand++;
        System.out.println("Der Freund " + vorname + " " + nachname + " wurde in der Kartei angelegt.");
        auswahlAnzeigen();
    }

    private void freundeAnzeigen() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("friends.tmp");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Freund> friends = (ArrayList<Freund>) ois.readObject();
        System.out.println(friends.get(0).getNachname()); 
        System.out.println(friends.get(1).getNachname()); 
        ois.close();
    }
    // ============================== Plus bas pas important

    public static void main(String[] args) throws Exception {
        Kartei kartei = null;
        // Hier Kartei kreeieren ? Wie ruft man methoden von der command line java ?
        if (args.length == 1) {
            kartei = new Kartei(args[0]);
            kartei.auswahlAnzeigen();
            // kartei.datenSpeichern(args[0]);
        } else {
            System.out.println("Aufruf mit: java Kartei friends.tmp");
        }

    }

    // Die Methode auswahlAnzeigen zeigt eine Auswahl an
    private void auswahlAnzeigen() throws Exception {
        System.out.println("Bitte Zahl und dann RETURN eingeben:");
        System.out.println("<1> Freund anlegen");
        System.out.println("<2> Freund suchen");
        System.out.println("<3> Freund veraendern");
        System.out.println("<4> Freund loeschen");
        System.out.println("<5> Anzahl gespeicherter Freunde angeben");
        System.out.println("<6> Alle Freunde anzeigen");
        System.out.println("<7> Beenden");
        auswahlAuswerten();
    }

    // Die Methode auswahlAuswerten wertet die Eingabe des Anwenders aus
    private void auswahlAuswerten() throws Exception {
        Scanner input = new Scanner(System.in); // Never closed ?
        int auswahl = input.nextInt();
        input.nextLine(); // Zeilenumbruch einlesen
        // input.close();

        switch (auswahl) {
            case 1: {
                freundAnlegen();
                break;
            }
            // Other functions to be made
            case 2: {
                freundeAnzeigen();
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
                freundeAnzeigen();
                break;
            }
            case 7: {
                // Hier Sachen speichern weil hier der einzige Ort zum Beenden des Programms
                // sein soll. Alle anderen rufen auswahl anzeigen wieder auf
                // das sollte in einer anderen Klasse sein zum Beispiel Verwaltung
                break;
            }
            default:
                System.out.println("Falsche Eingabe");
        }
    }

    /*
     * Jetzt habe ich den Vector Freund in dieser Klasse. Eigentlich sollte er in
     * der Freund Klasse sein. Ich könnte zum Beispiel die Funktion freund anlegen
     * nach Freund.java verschieben. Oder ich erstelle eine dritte Klasse zur
     * verwaltung
     */
    private void bestandAbfragen() throws Exception {
        System.out.println("Du hast " + bestand + " Freunde in deiner Kartei.");
        auswahlAnzeigen();
    }

}