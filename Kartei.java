
/*
Die Klasse Kartei verwaltet die gesamten Freunde
Version 1.0
Author: Leah Chercham
*/

/*
Notes:

- UML
- Bearbeitung eines Objektes
- mehrere Freunde finden + Auswählen können
- Text schreiben
*/

import java.io.*;
import java.util.Scanner;
import java.util.UUID;

public class Kartei {

    // Friends Datei anlegen
    public Kartei(String dateiName) throws Exception {
        File file = new File(dateiName);
        if (file.exists()) {
            return;
        } else {
            file.createNewFile();
        }
    }

    // Initial Array mit 100 Freunden
    Freund[] freunde = new Freund[100];

    // Freund speichern in Datei
    private void freundSpeichern(Freund freund) throws Exception {

        // Error handler: falls File leer, nicht versuchen Daten zu ziehen
        File file = new File("friends.tmp");
        if (file.length() != 0) {
            FileInputStream fis = new FileInputStream("friends.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            freunde = (Freund[]) ois.readObject();
        }

        for (int i = 0; i < freunde.length; i++) {
            if (freunde[i] == null) { // Findet leeren Eintrag und überschreibt ihn mit Freund
                freunde[i] = freund;
                break;
            }
        }

        FileOutputStream fos = new FileOutputStream("friends.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(freunde);
        oos.flush();
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

        // Create instance of Freund
        Freund f1 = new Freund(vorname, nachname, geburtstag, telefon, handy, adresse, schluessel);

        freundSpeichern(f1); // Array instead of friend
        System.out.println("....................................");
        System.out.println("Der Freund " + vorname + " " + nachname + " wurde in der Kartei angelegt.");
        System.out.println("....................................");
        auswahlAnzeigen();

    }

    private void freundeAnzeigen() throws Exception {
        File file = new File("friends.tmp");
        if (file.length() == 0) {
            fehlermeldungAnzeigen();
            return;
        }
        FileInputStream fis = new FileInputStream("friends.tmp");
        ObjectInputStream ois = new ObjectInputStream(fis);
        freunde = (Freund[]) ois.readObject();
        System.out.println("....................................");
        System.out.println("Telefonliste:");

        for (int i = 0; i < freunde.length; i++) {
            if (freunde[i] != null) { // Zeigt nur tatsächliche Einträge
                System.out.println(freunde[i].getName());
                System.out.println(" Handy: " + freunde[i].getHandy());
                System.out.println(" Telefon: " + freunde[i].getTelefon());
                System.out.println("..................");
            }
        }

        System.out.println("....................................");
        auswahlAnzeigen();
    }

    private void freundBearbeiten(Freund foundFriend) throws Exception {
        System.out.println("....................................");
        System.out.println("Was moechtest du verändern?");
        System.out.println("....................................");
        System.out.println("<1> Vorname: " + foundFriend.getVorname());
        System.out.println("<2> Nachname: " + foundFriend.getNachname());
        System.out.println("<3> Handy: " + foundFriend.getHandy());
        System.out.println("<4> Telefon: " + foundFriend.getTelefon());
        System.out.println("<5> Adresse: " + foundFriend.getAdresse());
        System.out.println("<6> Geburtstag: " + foundFriend.getGeburtstag());

        freundBearbeitenAuswahlAuswaerten(foundFriend);
    }

    private Freund freundFinden(String eingabe) throws Exception {
        File file = new File("friends.tmp");
        if (file.length() == 0) {
            fehlermeldungAnzeigen();
        }

        FileInputStream fis = new FileInputStream("friends.tmp");
        ObjectInputStream ois = new ObjectInputStream(fis);
        freunde = (Freund[]) ois.readObject();

        Freund foundFriend = null;

        for (int i = 0; i < freunde.length; i++) {
            if (freunde[i] != null) { // Findet leeren Eintrag und überschreibt ihn mit Freund
                if (freunde[i].getName().toUpperCase().contains(eingabe.toUpperCase())) {
                    foundFriend = freunde[i];
                }
            }
        }
        return foundFriend;
    }

    private void freundLoeschen() throws Exception {

        // gleiche wie freund SUchen - Anfang
        File file = new File("friends.tmp");
        if (file.length() == 0) {
            fehlermeldungAnzeigen();
            return;
        }
        System.out.println("....................................");
        System.out.println("Bitte Namen oder Vornamen eingeben und dann RETURN eingeben");
        System.out.println("....................................");

        Scanner input = new Scanner(System.in);
        String eingabe = input.nextLine();

        Freund foundFriend = freundFinden(eingabe);
        // gleiche wie Freund Suchen - Ende

        System.out.println("....................................");
        System.out.println("Möchtest du " + foundFriend.getName() + " aus der Kartei entfernen?");
        System.out.println("<1> Nein");
        System.out.println("<2> Ja");
        System.out.println("....................................");

        int auswahl = input.nextInt();

        switch (auswahl) {
            case 1: {
                auswahlAnzeigen();
            }
            case 2: {
                for (int i = 0; i < freunde.length; i++) {
                    if (freunde[i].getSchluessel() == foundFriend.getSchluessel()) { // Findet leeren Eintrag und
                                                                                     // überschreibt ihn mit Freund
                        freunde[i] = null;
                        break;
                    }
                }

                FileOutputStream fos = new FileOutputStream("friends.tmp");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(freunde);
                oos.flush();

                System.out.println("....................................");
                System.out.println(foundFriend.getName() + " wurde aus der Kartei entfernt.");
                System.out.println("....................................");

            }
        }

    }

    private void freundSuchen() throws Exception { // in AuswahlFreundVeraendern vielleicht implementieren (nicht
                                                   // doppelt code)
        File file = new File("friends.tmp");
        if (file.length() == 0) {
            fehlermeldungAnzeigen();
            return;
        }
        System.out.println("....................................");
        System.out.println("Bitte Namen oder Vornamen eingeben und dann RETURN eingeben");
        System.out.println("....................................");

        Scanner input = new Scanner(System.in);
        String eingabe = input.nextLine();

        Freund foundFriend = freundFinden(eingabe);
        if (foundFriend == null) {
            System.out.println(eingabe + " wurde nicht gefunden.");
            auswahlAnzeigen();
        }
        System.out.println("....................................");
        System.out.println("Name: " + foundFriend.getName());
        System.out.println("Handy: " + foundFriend.getHandy());
        System.out.println("Telefon: " + foundFriend.getTelefon());
        System.out.println("Adresse: " + foundFriend.getAdresse());
        System.out.println("Geburtstag: " + foundFriend.getGeburtstag());
        System.out.println("Schluessel: " + foundFriend.getSchluessel());
        System.out.println("....................................");

        auswahlAnzeigen();

    }

    private void fehlermeldungAnzeigen() throws Exception {
        System.out.println("....................................");
        System.out.println("Es sind keine Freunde in deiner Kartei.");
        System.out.println("....................................");
        auswahlAnzeigen();
    }

    private void freundBearbeitenAuswahlAuswaerten(Freund foundFriend) throws Exception {
        Scanner input = new Scanner(System.in);
        int auswahl = 0;
        if (input.hasNextInt()) {
            auswahl = input.nextInt(); // was wenn String
        } else {
            System.out.println("Please use a number between 1 and 7. You used: " + input.next());
            freundBearbeitenAuswahlAuswaerten(foundFriend);
        }
        input.nextLine();

        switch (auswahl) {
            case 1: {
                System.out.println("....................................");
                System.out.println("Bitte den neuen VORNAMEN eingeben und mit RETURN bestaetigen");
                System.out.println("....................................");

                Scanner newInput = new Scanner(System.in);
                String eingabe = foundFriend.getVorname();
                if (newInput.hasNextLine()) {
                    eingabe = newInput.nextLine();
                }
                foundFriend.setVorname(eingabe);
                karteiAktualisieren(foundFriend);
                System.out.println("Vorname geaendert zu : " + foundFriend.getVorname());

            }
        }

    }

    private void karteiAktualisieren(Freund freund) throws Exception {
        for (int i = 0; i < freunde.length; i++) {
            if (freunde[i] != null) { // Zeigt nur tatsächliche Einträge
                if (freunde[i].getSchluessel() == freund.getSchluessel()) {
                    freunde[i] = freund;
                }
            }
        }
        FileOutputStream fos = new FileOutputStream("friends.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(freunde);
        oos.flush();
    }

    private void auswahlFreundVeraendern() throws Exception {
        System.out.println("....................................");
        System.out.println("Bitte Namen oder Vornamen eingeben und dann RETURN eingeben");
        System.out.println("....................................");

        Scanner input = new Scanner(System.in);
        String eingabe = input.nextLine();

        Freund foundFriend = freundFinden(eingabe); // sollte freund returnen
        if (foundFriend == null) {
            System.out.println(eingabe + " wurde nicht gefunden.");
            auswahlAnzeigen();
        }

        System.out.println("Wir haben diesen Freund gefunden: " + foundFriend.getName());
        System.out.println("Moechtest du diesen Freund veraendern?");
        System.out.println("<1> Ja");
        System.out.println("<2> Nein");

        if (input.hasNextInt()) {
            String auswahl = input.next();
            switch (auswahl) {
                case "1": {
                    freundBearbeiten(foundFriend);
                    break;
                }
                case "2": {
                    auswahlAnzeigen();
                    break;
                }
            }
        }

        auswahlAnzeigen();
    }
    // ============================== Plus bas pas important

    public static void main(String[] args) throws Exception {
        // Hauptmethode (wird ausgeführt beim Aufruf)
        Kartei kartei = null;
        if (args.length == 1) {
            kartei = new Kartei(args[0]);
            kartei.auswahlAnzeigen();
        } else {
            System.out.println("....................................");
            System.out.println("Aufruf mit: java Kartei friends.tmp");
            System.out.println("....................................");
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
        System.out.println("<6> Telefonliste ausgeben");
        System.out.println("<7> Beenden");
        auswahlAuswerten();
    }

    // Die Methode auswahlAuswerten wertet die Eingabe des Anwenders aus
    private void auswahlAuswerten() throws Exception {
        Scanner input = new Scanner(System.in);
        int auswahl = 0;
        if (input.hasNextInt()) {
            auswahl = input.nextInt(); // was wenn String
        } else {
            System.out.println("Please use a number between 1 and 7. You used: " + input.next());
            auswahlAuswerten();
        }
        input.nextLine(); // Zeilenumbruch einlesen

        switch (auswahl) {
            case 1: {
                freundAnlegen();
                break;
            }
            // Other functions to be made
            case 2: {
                freundSuchen();
                break;

            }
            case 3: {
                auswahlFreundVeraendern();
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
                System.out.println("....................................");
                System.out.println("Falsche Eingabe");
                System.out.println("....................................");
                auswahlAnzeigen();
        }
    }

    private void bestandAbfragen() throws Exception {

        File file = new File("friends.tmp");
        if (file.length() != 0) {
            FileInputStream fis = new FileInputStream("friends.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            freunde = (Freund[]) ois.readObject();
            int bestand = 0;

            for (int i = 0; i < freunde.length; i++) {
                if (freunde[i] != null) { // Zeigt nur tatsächliche Einträge
                    bestand++;
                }
            }

            System.out.println("....................................");
            System.out.println("Du hast " + bestand + " Freunde in deiner Kartei.");
            System.out.println("....................................");
        } else {
            fehlermeldungAnzeigen();
        }

        auswahlAnzeigen();
    }

}