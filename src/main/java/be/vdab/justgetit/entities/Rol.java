package be.vdab.justgetit.entities;

import javax.persistence.*;

@Entity
@Table(name = "rollen")
public enum Rol {
    MANAGER(1, "manager"), BEDIENDE(2,"bediende"), KLANT(3,"klant");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String naam;

    Rol(int id,String naam) {
        this.id = id;
        this.naam = naam;
    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
