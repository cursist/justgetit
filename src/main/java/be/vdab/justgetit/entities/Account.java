package be.vdab.justgetit.entities;

import javax.persistence.*;

@Embeddable
@Table(name = "klantenaccounts")
public class Account {
    private String accountnaam;
    private String wachtwoord;
}
