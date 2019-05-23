package be.vdab.justgetit.manager.forms;

import javax.persistence.Embeddable;

@Embeddable
public class CategorieNaam {
    private String naam;

    public CategorieNaam(String naam) {
        this.naam = naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }
}
