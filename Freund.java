import java.io.Serializable;

/*
Die Klasse Freund verwaltet Freundesdaten
Version 1.0
Author: Leah Chercham
*/

public class Freund implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String vorname;
    private String nachname;
    private String geburtstag;
    private int telefon;
    private int handy;
    private String adresse; // Als Klasse ? Braucht PLZ Ort und Str.
    private String schluessel; // eindeutige identifizierung mit stringified UUID

    public Freund(String vorname, String nachname, String geburtstag, int telefon, int handy, String adresse,
            String schluessel) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtstag = geburtstag;
        this.telefon = telefon;
        this.handy = handy;
        this.adresse = adresse;
        this.schluessel = schluessel;
    }

    public void modifyFriend(String attribute, String text, int nummern) {
        // Switch statement for attribute, pass value
        switch (attribute) {
            case "vorname": {
                this.setVorname(text);
                break;
            }
            case "nachname": {
                this.setNachname(text);
                break;
            }
            case "geburtstag": {
                this.setGeburtstag(text);
                break;
            }
            case "telefon": {
                this.setTelefon(nummern);
                break;
            }
            case "handy": {
                this.setHandy(nummern);
                break;
            }
            case "adresse": {
                this.setAdresse(text);
                break;
            }
        }
    }


    // ********* Set methods
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setGeburtstag(String geburtstag) {
        this.geburtstag = geburtstag;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public void setHandy(int handy) {
        this.handy = handy;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    // ********* Get methods
    public String getName() {
        return (vorname + " " + nachname);
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getGeburtstag() {
        return geburtstag;
    }

    public int getHandy() {
        return handy;
    }

    public int getTelefon() {
        return telefon;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getSchluessel() {
        return schluessel;
    }
}
