package be.vdab.justgetit.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name= "klanten")
public class Klant implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long klantId;
    private String voornaam;
    private String achternaam;
    private String adres;
    @Column(name="telefoonnummer", unique = true)
    private String telefoonnummer;
    @Column(name="email", unique = true)
    private String email;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="gemeenteId")
    private Gemeente gemeente;

    private String accountnaam;
    private String wachtwoord;

    @ManyToMany
    @JoinTable(name = "gebruikersrollen",
               joinColumns = @JoinColumn(name = "klantId"),
               inverseJoinColumns = @JoinColumn(name = "rolId"))
    private Set<Rol> rollen = new HashSet<>();

    protected Klant() {}

    public Klant(String voornaam, String achternaam, String adres,
                 String telefoonnummer, String email, Gemeente gemeente) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.adres = adres;
        this.telefoonnummer = telefoonnummer;
        this.email = email;
        this.gemeente = gemeente;
        rollen.add(Rol.KLANT);
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

    public void setAccount(String accountnaam, String wachtwoord) {
        this.accountnaam = accountnaam;
        this.wachtwoord = wachtwoord;
    }

    public String getAccountnaam() {
        return accountnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public Set<Rol> getRollen() {
        return Collections.unmodifiableSet(rollen);
    }

    public void replaceRollen(Set<Rol> nieuweRollen) {
        this.rollen = nieuweRollen;
    }

    public void setGemeente(Gemeente gemeente) {
        this.gemeente = gemeente;
    }

    public void setWachtwoord(String string){
        this.wachtwoord = string;
    }
}
