package be.vdab.justgetit.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name= "klanten")
public class Klant implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long klantId;
    private String voornaam;
    private String achternaam;
    //private  String naam;
    private String adres;
    @Column(name="telefoonnummer", unique = true)
    private String telefoonnummer;
    @Column(name="email", unique = true)
    private String email;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="gemeenteId")
    private Gemeente gemeente;

    @Embedded
    private Account account;

    protected Klant() {}

    public Klant(String voornaam, String achternaam, String adres,
                 String telefoonnummer, String email, Gemeente gemeente) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.adres = adres;
        this.telefoonnummer = telefoonnummer;
        this.email = email;
        this.gemeente = gemeente;
//        setNaam(voornaam, achternaam);
    }

    public long getKlantId() {
        return klantId;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    /*public String getNaam() {
        return naam;
    }*/

    /*public void setNaam(String voornaam, String achternaam) {
        this.naam = voornaam + achternaam;
    }*/

    public String getAdres() {
        return adres;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public String getEmail() {
        return email;
    }

    public Gemeente getGemeente() {
        return gemeente;
    }

}
