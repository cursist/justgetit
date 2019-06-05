package be.vdab.justgetit.valueobjects;

import javax.persistence.*;

@Embeddable
@Table(name = "klantenaccounts")
public class Account {
    private String accountnaam;
    private String wachtwoord;
}
