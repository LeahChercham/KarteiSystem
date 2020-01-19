
import java.util.Vector;

/*
Die Klasse Freund verwaltet Freundesdaten
Version 1.0
Author: Leah Chercham
*/

public class Freund {
    private String vorname;
    private String nachname;
    private String geburtstag;
    private int telefon;
    private int handy;
    private String adresse; // Als Klasse ? Braucht PLZ Ort und Str.
    private String schluessel; // eindeutige identifizierung

    // vector: growable array


    // public String getFreund() {
    //     // return Friend infos
    //     return "jaja";
    // }

    // 2 Methoden um Vor und Nachname zu setzen / vielleicht für änderungen benutzen.
    // public void setVorname(String n) {
    //     vorname = n;
    // }

    // public void setNachname(String n) {
    //     nachname = n;
    // }

    public String getName() {
        return (vorname + " " + nachname);
    }

    // Konstruktor, automatisch durch new-Operator aufgerufen - new Operator in
    // klasse Kartei ?
    public Freund(String vorname, String nachname, String geburtstag, int telefon, int handy, String adresse,
            String schluessel) // ... alle auflisten
    {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtstag = geburtstag;
        this.telefon = telefon;
        this.handy = handy;
        this.adresse = adresse;
        this.schluessel = schluessel;
    }
}
