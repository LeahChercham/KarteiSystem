import java.io.Serializable;

/**
 * Die Klasse Freund speichert die Freundesdaten.
 * 
 * @version 1.0
 * @author Leah Chercham
 */

public class Freund implements Serializable {

    // Attribute
    private String vorname;
    private String nachname;
    private String geburtstag;
    private String telefon;
    private String handy;
    private String adresse;
    private String schluessel;

    // Konstruktor
    public Freund(String vorname, String nachname, String geburtstag, String telefon, String handy, String adresse,
            String schluessel) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtstag = geburtstag;
        this.telefon = telefon;
        this.handy = handy;
        this.adresse = adresse;
        this.schluessel = schluessel;
    }

    // Methoden
    // Die Set-Methoden nehmen Veränderungen an der Instanz der Klasse vor.

    /**
     * Die Methode setNachname() veraendert den Nachnamen des Freundes.
     * 
     * @param nachname Nachname des Freundes.
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * Die Methode setVorname() veraendert den Vornamen des Freundes.
     * 
     * @param vorname Vorname des Freundes.
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * Die Methode setGeburtstag() veraendert den Geburtstag des Freundes.
     * 
     * @param geburtstag Geburtstag des Freundes.
     */
    public void setGeburtstag(String geburtstag) {
        this.geburtstag = geburtstag;
    }

    /**
     * Die Methode setTelefon() veraendert die Telefonnummer des Freundes.
     * 
     * @param telefon Telefon des Freundes.
     */
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    /**
     * Die Methode setHandy() veraendert die Handynummer des Freundes.
     * 
     * @param handy Handy des Freundes.
     */
    public void setHandy(String handy) {
        this.handy = handy;
    }

    /**
     * Die Methode setAdresse() veraendert die Adresse des Freundes.
     * 
     * @param adresse Adresse des Freundes.
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    // Die Set-Methoden nehmen Veränderungen an der Instanz der Klasse vor.

    /**
     * Die Methode getName() gibt den vollstaendigen Namen des Freundes zurück.
     * 
     * @return vollstaendiger Name der Person von Typ String.
     */
    public String getName() {
        return (vorname + " " + nachname);
    }

    /**
     * Die Methode getVorname() gibt den Vornamen des Freundes zurück.
     * 
     * @return vorname der Person von Typ String.
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * Die Methode getNachname() gibt den Nachnamen des Freundes zurück.
     * 
     * @return nachname der Person von Typ String.
     */
    public String getNachname() {
        return nachname;
    }

    /**
     * Die Methode getGeburtstag() gibt den Geburtstag des Freundes zurück.
     * 
     * @return geburtstag der Person von Typ String.
     */
    public String getGeburtstag() {
        return geburtstag;
    }

    /**
     * Die Methode getHandy() gibt die Handynummer des Freundes zurück.
     * 
     * @return handy der Person von Typ int.
     */
    public String getHandy() {
        return handy;
    }

    /**
     * Die Methode getTelefon() gibt die Telefonnummer des Freundes zurück.
     * 
     * @return telefon der Person von Typ int.
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * Die Methode getAdresse() gibt die Adresse des Freundes zurück.
     * 
     * @return Adresse der Person von Typ String.
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Die Methode getSchluessel() gibt die Schluessel des Freundes zurück.
     * 
     * @return Schluessel der Person von Typ String.
     */
    public String getSchluessel() {
        return schluessel;
    }
}
