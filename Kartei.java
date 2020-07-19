
/** Die Klasse Kartei verwaltet die Freundesdaten.
 * @version 1.0
 * @author Leah Chercham
 */

/*
Notes:
- UML
- Bearbeitung eines Objektes
- Text schreiben
*/

import java.io.*;
import java.util.Scanner;
import java.util.UUID;

public class Kartei {

    /**
     * Die Methode main() wird beim Aufruf ausgeführt. Sie erstellt eine Instanz der
     * Klasse Kartei. Sie zeigt als erste das Auswahlmenu an.
     */
    public static void main(String[] args) throws Exception {
        Kartei kartei = null;
        if (args.length == 1) {
            kartei = new Kartei(args[0]);
            kartei.auswahlAnzeigen();
        } else {
            System.out.println("Aufruf mit: java Kartei friends.tmp");

        }
    }

    /**
     * Die Methode Kartei() prüft ob die Kartei bereits existiert und erstellt eine
     * falls nicht.
     * 
     * @param dateiName Name der Datei.
     */
    public Kartei(String dateiName) throws Exception {
        File file = new File(dateiName);
        if (file.exists()) {
            return;
        } else {
            file.createNewFile();
        }
    }

    // Die Kartei enthält Platz zur Speicherung von 100 Freunden.
    Freund[] freunde = new Freund[100];

    /**
     * Die Methode auswahlAnzeigen() zeigt die verschiedenen Optionen an von denen
     * der Benutzer auswaehlen kann.
     */
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

    /**
     * Die Methode auswahlAuswerten() wertet die Auswahl des Benutzers aus und
     * fuehrt die entsprechenden Methoden aus.
     */
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
                neuAnlegen();
                break;
            }
            case 2: {
                freundDetailsAnzeigen();
                break;
            }
            case 3: {
                auswahlFreundVeraendern();
                break;
            }
            case 4: {
                loeschen();
                break;
            }
            case 5: {
                bestandAbfragen();
                break;
            }
            case 6: {
                alleAnzeigen();
                break;
            }
            case 7: {
                System.exit(0);
                break;
            }
            default:
                System.out.println("Falsche Eingabe");
                auswahlAnzeigen();
        }
    }

    /**
     * Die Methode speichern() aktualisiert die Kartei friends.tmp mit den neuen
     * Daten aus der Methode neuAnlegen().
     * 
     * @param freund eine Instanz der Klasse Freund.
     */
    private void speichern(Freund freund) throws Exception {

        File file = new File("friends.tmp");
        // Falls die Datei leer ist wird nicht versucht die Datei zu lesen.
        if (file.length() != 0) {
            FileInputStream fis = new FileInputStream("friends.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            freunde = (Freund[]) ois.readObject();
        }

        // Findet leeren Eintrag und überschreibt ihn mit Freund
        for (int i = 0; i < freunde.length; i++) {
            if (freunde[i] == null) {
                freunde[i] = freund;
                break;
            }
        }

        FileOutputStream fos = new FileOutputStream("friends.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(freunde);
        oos.flush();
    }

    /**
     * Die Methode neuAnlegen() nimmt die Daten ueber den neuen Freund auf. Die
     * Daten werden anschliessend als Parameter an die Methode speichern() gegeben.
     */
    private void neuAnlegen() throws Exception {
        Scanner eingabe = new Scanner(System.in);
        System.out.print("Vorname eingeben: ");
        String vorname = eingabe.next();
        System.out.print("Nachname eingeben: ");
        String nachname = eingabe.next();
        System.out.print("Geburtstag eingeben: ");
        String geburtstag = eingabe.next();
        System.out.print("Handy Nummer eingeben: ");
        String handy = eingabe.next();
        System.out.print("Telefon Nummer eingeben: ");
        String telefon = eingabe.next();
        System.out.print("Adresse eingeben: ");
        String adresse = eingabe.next();
        String schluessel = UUID.randomUUID().toString();

        // Erstellung einer neuen Instanz der Klasse Freund
        Freund f1 = new Freund(vorname, nachname, geburtstag, telefon, handy, adresse, schluessel);

        speichern(f1);

        System.out.println("Der Freund " + vorname + " " + nachname + " wurde in der Kartei angelegt.");

        auswahlAnzeigen();
    }

    /**
     * Die Methode alleAnzeigen() gibt die Telefonliste aus.
     */
    private void alleAnzeigen() throws Exception {
        File file = new File("friends.tmp");
        if (file.length() == 0) {
            fehlermeldungAnzeigen();
            return;
        }
        FileInputStream fis = new FileInputStream("friends.tmp");
        ObjectInputStream ois = new ObjectInputStream(fis);
        freunde = (Freund[]) ois.readObject();
        System.out.println("Telefonliste:");

        for (int i = 0; i < freunde.length; i++) {
            if (freunde[i] != null) { // Zeigt nur tatsächliche Einträge
                System.out.println(freunde[i].getName());
                System.out.println(" Handy: " + freunde[i].getHandy());
                System.out.println(" Telefon: " + freunde[i].getTelefon());
                System.out.println("..................");
            }
        }

        auswahlAnzeigen();
    }

    /**
     * Die Methode einenFreundSuchen() sucht einen Freund aufgrund von einer Eingabe
     * 
     * @param eingabe Eingabe des Benutzers, Vorname oder Nachname eines Freundes.
     * @return foundFriend gefundener Freund des Typs Freund
     */
    private Freund einenFreundSuchen(String eingabe) throws Exception {
        File file = new File("friends.tmp");
        if (file.length() == 0) {
            fehlermeldungAnzeigen();
        }

        FileInputStream fis = new FileInputStream("friends.tmp");
        ObjectInputStream ois = new ObjectInputStream(fis);
        freunde = (Freund[]) ois.readObject();

        Freund foundFriend = null;

        for (int i = 0; i < freunde.length; i++) {
            if (freunde[i] != null) { // ueberspringt leere Eintraege
                if (freunde[i].getName().toUpperCase().contains(eingabe.toUpperCase())) {
                    foundFriend = freunde[i];
                }
            }
        }
        return foundFriend;
    }

    /**
     * Die Methode loeschen() ersetzt den Freund durch null.
     */
    private void loeschen() throws Exception {
        Freund foundFriend = eingabeZumSuchen();

        System.out.println("Moechtest du " + foundFriend.getName() + " aus der Kartei entfernen?");
        System.out.println("<1> Nein");
        System.out.println("<2> Ja");

        Scanner input = new Scanner(System.in);
        int auswahl = input.nextInt();

        switch (auswahl) {
            case 1: {
                auswahlAnzeigen();
            }
            case 2: {
                for (int i = 0; i < freunde.length; i++) {
                    if (freunde[i].getSchluessel() == foundFriend.getSchluessel()) { // Findet leeren Eintrag und
                        // überschreibt ihn mit null
                        freunde[i] = null;
                        break;
                    }
                }

                FileOutputStream fos = new FileOutputStream("friends.tmp");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(freunde);
                oos.flush();

                System.out.println(foundFriend.getName() + " wurde aus der Kartei entfernt.");

                auswahlAnzeigen();
            }
        }

    }

    /**
     * Die Methode eingabeZumSuchen() fordert den Benutzer auf, einen Freund zu
     * suchen.
     * 
     * @return foundFriend gefundener Freund des Typs Freund
     */
    private Freund eingabeZumSuchen() throws Exception {
        File file = new File("friends.tmp");
        if (file.length() == 0) {
            fehlermeldungAnzeigen();
            auswahlAnzeigen();
        }

        System.out.println("Bitte Namen oder Vornamen eingeben und dann RETURN eingeben");

        Scanner input = new Scanner(System.in);
        String eingabe = input.nextLine();

        Freund foundFriend = einenFreundSuchen(eingabe);
        if (foundFriend == null) {
            System.out.println(eingabe + " wurde nicht gefunden.");
            auswahlAnzeigen();
        }
        return foundFriend;
    }

    /**
     * Die Methode freundDetailsAnzeigen() sucht einen Freund dank der Methode
     * eingabeZumSuchen und gibt anschliessend alle Informationen ueber diesen
     * Freund aus.
     */
    private void freundDetailsAnzeigen() throws Exception {
        Freund foundFriend = eingabeZumSuchen();

        System.out.println("Name: " + foundFriend.getName());
        System.out.println("Handy: " + foundFriend.getHandy());
        System.out.println("Telefon: " + foundFriend.getTelefon());
        System.out.println("Adresse: " + foundFriend.getAdresse());
        System.out.println("Geburtstag: " + foundFriend.getGeburtstag());
        System.out.println("Schluessel: " + foundFriend.getSchluessel());

        auswahlAnzeigen();
    }

    /**
     * Die Methode fehlermeldungAnzeigen() wird ausgelöst wenn die Datei friends.tmp
     * eine Laenge von 0 hat und besagt dass keine Freunde in der Kartei angelegt
     * sind.
     */
    private void fehlermeldungAnzeigen() throws Exception {
        System.out.println("Es sind keine Freunde in deiner Kartei.");
        auswahlAnzeigen();
    }

    /**
     * Die Methode bearbeiten() ermoeglicht aenderungen an einer Freundesinstanz
     * vorzunehmen
     */
    private void bearbeiten(Freund foundFriend) throws Exception {
        System.out.println("Was moechtest du veraendern?");
        System.out.println("<1> Vorname: " + foundFriend.getVorname());
        System.out.println("<2> Nachname: " + foundFriend.getNachname());
        System.out.println("<3> Handy: " + foundFriend.getHandy());
        System.out.println("<4> Telefon: " + foundFriend.getTelefon());
        System.out.println("<5> Adresse: " + foundFriend.getAdresse());
        System.out.println("<6> Geburtstag: " + foundFriend.getGeburtstag());

        bearbeitenAuswahlAuswaerten(foundFriend);
    }

    /**
     * Die Methode bearbeitenAuswahlAuswaerten() ermoeglicht die Bearbeitung einer
     * Freundesinstanz je nach Eingabe des Benutzers.
     */
    private void bearbeitenAuswahlAuswaerten(Freund foundFriend) throws Exception {
        Scanner input = new Scanner(System.in);
        int auswahl = 0;
        if (input.hasNextInt()) {
            auswahl = input.nextInt(); // was wenn String
        } else {
            System.out.println("Please use a number between 1 and 6. You used: " + input.next());
            bearbeitenAuswahlAuswaerten(foundFriend);
        }
        input.nextLine();

        switch (auswahl) {
            case 1: {

                System.out.println("Bitte den neuen VORNAMEN eingeben und mit RETURN bestaetigen");

                Scanner newInput = new Scanner(System.in);
                String eingabe = foundFriend.getVorname();
                if (newInput.hasNextLine()) {
                    eingabe = newInput.nextLine();
                }
                foundFriend.setVorname(eingabe);
                karteiAktualisieren(foundFriend);
                System.out.println("Vorname geaendert zu : " + foundFriend.getVorname());
                break;
            }
            case 2: {

                System.out.println("Bitte den neuen NACHNAMEN eingeben und mit RETURN bestaetigen");

                Scanner newInput = new Scanner(System.in);
                String eingabe = foundFriend.getNachname();
                if (newInput.hasNextLine()) {
                    eingabe = newInput.nextLine();
                }
                foundFriend.setNachname(eingabe);
                karteiAktualisieren(foundFriend);
                System.out.println("Nachname geaendert zu : " + foundFriend.getNachname());
                break;
            }
            case 3: {

                System.out.println("Bitte den neuen HANDY eingeben und mit RETURN bestaetigen");

                Scanner newInput = new Scanner(System.in);
                String eingabe = foundFriend.getHandy();
                if (newInput.hasNext()) {
                    eingabe = newInput.next();
                }
                foundFriend.setHandy(eingabe);
                karteiAktualisieren(foundFriend);
                System.out.println("Handy geaendert zu : " + foundFriend.getHandy());
                break;
            }
            case 4: {
                System.out.println("Bitte den neuen TELEFON eingeben und mit RETURN bestaetigen");
                Scanner newInput = new Scanner(System.in);
                String eingabe = foundFriend.getTelefon();
                if (newInput.hasNext()) {
                    eingabe = newInput.next();
                }
                foundFriend.setTelefon(eingabe);
                karteiAktualisieren(foundFriend);
                System.out.println("Telefon geaendert zu : " + foundFriend.getTelefon());
                break;
            }
            case 5: {

                System.out.println("Bitte den neuen ADRESSE eingeben und mit RETURN bestaetigen");

                Scanner newInput = new Scanner(System.in);
                String eingabe = foundFriend.getAdresse();
                if (newInput.hasNextLine()) {
                    eingabe = newInput.nextLine();
                }
                foundFriend.setAdresse(eingabe);
                karteiAktualisieren(foundFriend);
                System.out.println("Adresse geaendert zu : " + foundFriend.getAdresse());
                break;
            }
        }

    }

    /**
     * Die Methode karteiAktualisieren() speichert die Veraenderungen die an einer
     * Instanz vorgenommen worden sind in der friends.tmp Datei
     */
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

    /**
     * Die Methode auswahlFreundVeraendern() sucht zuerst nach einem Freund und
     * ermoeglicht dann dessen Veraenderung.
     */
    private void auswahlFreundVeraendern() throws Exception {
        System.out.println("Bitte Namen oder Vornamen eingeben und dann RETURN eingeben");

        Scanner input = new Scanner(System.in);
        String eingabe = input.nextLine();

        Freund foundFriend = einenFreundSuchen(eingabe); // sollte freund returnen
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
                    bearbeiten(foundFriend);
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

    /**
     * Die Methode bestandAbfragen() gibt den Benutzer Auskunft ueber die Anzahl der
     * gespeicherten Freunde in der Kartei.
     */
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
            System.out.println("Du hast " + bestand + " Freunde in deiner Kartei.");
        } else {
            fehlermeldungAnzeigen();
        }
        auswahlAnzeigen();
    }

}