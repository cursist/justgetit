package be.vdab.justgetit.entities;

import javax.persistence.*;

@Entity
@Table(name = "rollen")
public class Rol {
    public enum RolEnum {
        MANAGER, BEDIENDE, KLANT
    }
    @Id
    private long id;
    @Enumerated(EnumType.STRING)
    private RolEnum rol;

    public Rol() {
        this.rol = RolEnum.KLANT;
    }
}
