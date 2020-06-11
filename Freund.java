import java.io.Serializable;

/*
Die Klasse Freund verwaltet Freundesdaten
Version 1.0
Author: Leah Chercham
*/

public class Freund implements Serializable {
    private String vorname;
    private String nachname;
    private String geburtstag;
    private int telefon;
    private int handy;
    private String adresse; // Als Klasse ? Braucht PLZ Ort und Str.
    private String schluessel; // eindeutige identifizierung mit stringified UUID
    
    public Freund(String vorname, String nachname, String geburtstag, int telefon, int handy, String adresse,
    String schluessel)
    {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtstag = geburtstag;
        this.telefon = telefon;
        this.handy = handy;
        this.adresse = adresse;
        this.schluessel = schluessel;
    }

    // public String toString(){
    //     return vorname + " " + nachname + " "  + geburtstag + " " + telefon + " " + handy + " " + adresse + " " + schluessel;
    // }
    
    // ============================== Plus bas pas important
    
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
    
        public String getVorname(){
            return vorname;
        }
        public String getNachname(){
            return nachname;
        }
        public String getGeburtstag(){
            return geburtstag;
        }
        public int getHandy(){
            return handy;
        }
        public int getTelefon(){
            return telefon;
        }
        public String getAdresse(){
            return adresse;
        }
        public String getSchluessel(){
            return schluessel;
        }
}
